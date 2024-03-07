package org.example.smstool.common;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.smstool.entity.SmsappUserinfo;
import org.example.smstool.exception.ServiceException;
import org.example.smstool.service.ISmsappUserinfoService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;



public class JwtInterceptor implements HandlerInterceptor {
    @Resource
    private ISmsappUserinfoService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String Authorization = request.getHeader("Authorization"); //获取请求头 Authorization
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if (StrUtil.isBlank(Authorization)) {
            throw new ServiceException(Constants.CODE_401,"无token，请重新登录");
        }

        // 获取 Authorization 中的 user id
        String userId;
        try {
            userId = JWT.decode(Authorization).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException(Constants.CODE_401,"无token，请重新登录");
        }
        //根据Authorization中的userId查询数据库
        SmsappUserinfo user = userService.getById(userId);
        if (user == null) {
             throw new ServiceException(Constants.CODE_401,"用户不存在，请重新登录");
        }
        // 用户密码加签验证 Authorization
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//        try {
//            jwtVerifier.verify(Authorization);
//        } catch (JWTVerificationException e) {
//            throw new ServiceException(Constants.CODE_401,"无token，请重新登录");
//        }

        return true;
    }
}