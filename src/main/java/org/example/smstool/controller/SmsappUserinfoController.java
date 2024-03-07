package org.example.smstool.controller;


import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.example.smstool.common.Constants;
import org.example.smstool.common.Result;
import org.example.smstool.controller.dto.WxLogin;
import org.example.smstool.entity.SmsappUserinfo;
import org.example.smstool.exception.ServiceException;
import org.example.smstool.service.ISmsappUserinfoService;
import org.example.smstool.utils.AESUtils;
import org.example.smstool.utils.GetPostUntil;
import org.example.smstool.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 小肚
 * @since 2024-03-06
 */
@RestController
@RequestMapping("/userinfo")
public class SmsappUserinfoController {
    @Autowired
    private ISmsappUserinfoService userinfoService;

    /**
     * 微信小程序登录获取
     * 获取session_key
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/getSessionKey")
    public Result ResultBeaninitWxLogin(@RequestBody WxLogin wxLogin) throws JSONException, InvalidAlgorithmParameterException, UnsupportedEncodingException {

        //微信获取session_key接口地址
        String wxLoginUrl = "https://api.weixin.qq.com/sns/jscode2session";
        //接口参数
        String param = "appid=wx721656e2f1d02e7d&secret=540982264045c098a0cf72872a4a8ab9&js_code=" + wxLogin.getJs_code() + "&grant_type=authorization_code";
        //调用获取session_key接口 请求方式get
        String jsonString = GetPostUntil.sendGet(wxLoginUrl, param);
        //因为json字符串是大括号包围，所以用JSONObject解析
        JSONObject json = new JSONObject(jsonString);
        //json解析session_key值
        String session_key =  json.getStr("session_key");
        String openid = json.getStr("openid");
        String phoneNumber = this.decodeUserInfo(wxLogin.getEncryptedData(), wxLogin.getIv(), session_key);
        if (phoneNumber.length() == 11){
            //异步执行
            CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
                SmsappUserinfo smsappUserinfo = new SmsappUserinfo();
                smsappUserinfo.setUrl(wxLogin.getUrl());
                smsappUserinfo.setName(wxLogin.getName());
                smsappUserinfo.setPhone(phoneNumber);
                smsappUserinfo.setSessionKey(session_key);
                smsappUserinfo.setOpenId(openid);
                userinfoService.saveOrUpdate(smsappUserinfo);
            });
            String token = TokenUtils.genToken(openid, phoneNumber);
            HashMap<String, String> data = new HashMap<>();
            data.put("phoneNumber",phoneNumber);
            data.put("token",token);
            return Result.success(data);
        }
        //返回给前端
        return Result.error(Constants.CODE_400,"系统错误");
    }


    /**
     * 解密小程序用户敏感数据
     *
     * @param encryptedData 明文
     * @param iv            加密算法的初始向量
     * @param sessionKey    用户秘钥
     * @return
     */
    public String decodeUserInfo( String encryptedData,
                                  String iv,
                                  String sessionKey
    ) throws UnsupportedEncodingException, InvalidAlgorithmParameterException, JSONException {
        //AESUtils微信获取手机号解密工具类
        AESUtils aes = new AESUtils();
        //调用AESUtils工具类decrypt方法解密获取json串
        byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
        //判断返回参数是否为空
        if (null != resultByte && resultByte.length > 0) {
            String jsons = new String(resultByte, "UTF-8");
            JSONObject json = new JSONObject(jsons);
            return json.getStr("phoneNumber");
        }
        return "session_key:失败";
    }

}

