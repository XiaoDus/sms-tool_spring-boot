package org.example.smstool.service.impl;

import org.example.smstool.entity.Files;
import org.example.smstool.mapper.FileMapper;
import org.example.smstool.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文件管理 服务实现类
 * </p>
 *
 * @author 小肚
 * @since 2024-03-06
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, Files> implements IFileService {

}
