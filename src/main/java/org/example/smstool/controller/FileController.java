package org.example.smstool.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.smstool.entity.Files;
import org.example.smstool.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 文件管理 前端控制器
 * </p>
 *
 * @author 小肚
 * @since 2024-01-12
 */
@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${files.upload.path}") //yml配置文件中的文件保存路径
    private String fileUploadPath;
    @Autowired
    private IFileService fileService;


    @PostMapping("/upload")
    public String upFile(@RequestParam MultipartFile file) throws IOException {
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //获取文件类型（后缀名）
        String type = FileUtil.extName(originalFilename);
        //文件大小
        long size = file.getSize();

        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;  //StrUtil.DOT ==>uuid + . + png
        File uploadUploadFiles = new File(fileUploadPath + fileUUID);

        //判断父级目录（/files/+1.png）是否存在，否则新建改文件目录
        if (!uploadUploadFiles.getParentFile().exists()) {
            uploadUploadFiles.getParentFile().mkdirs();
        }

        //获取文件下载路径
        String url;
        //把获取到的文件存储到磁盘目录
        file.transferTo(uploadUploadFiles);
        //获取文件的md5
        String md5 = SecureUtil.md5(uploadUploadFiles);
        //判断数据库是否存在相同记录
        Files one = getOneByMd5(md5);
        if (one != null) {
            url = one.getUrl();
            //由于文件已存在，则删除刚上传的重复文件
            uploadUploadFiles.delete();
        } else {
            //数据库若不存在重复文件，则不删除刚才上传的文件
            url = "http://127.0.0.1:2000/files/" + fileUUID;
        }

        //存储到数据库
        Files files = new Files();
        files.setName(originalFilename);
        files.setType(type);
        files.setSize(size / 1024); //单位：kb
        files.setUrl(url);
        files.setMd5(md5);
        fileService.save(files);

        return url;
    }

    /**
     * 下载接口  http://127.0.0.1:2000/files/" + fileUUID;
     *
     * @return
     */
//    @GetMapping("/{fileUUID}")
//    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
//        //根据文件的唯一标识获取文件
//        Files uploadUploadFiles = new Files(fileUploadPath + fileUUID);
//        //设置输出流格式
//        ServletOutputStream os = response.getOutputStream();
//        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
//        response.setContentType("application/octet-stream");
//
//        //读取文件的字节流
//        os.write(FileUtil.readBytes(uploadUploadFiles));
//        os.flush();
//        os.close();
//
//    }

    public Files getOneByMd5(String md5) {
        QueryWrapper<Files> fileQueryWrapper = new QueryWrapper<>();
        fileQueryWrapper.eq("md5", md5);
        List<Files> filesList = fileService.list(fileQueryWrapper);
        return filesList.size() == 0 ? null : filesList.get(0);
    }
}