package com.zengyanyu.system.controller;

import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.util.JwtUtil;
import com.zengyanyu.system.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Api(tags = "Token控制器")
@RequestMapping("/token")
public class TokenController {

    private static final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxODg4ODg4ODg4OCIsImV4cCI6MTc0MjExNDc5NCwiaWF0IjoxNzM5NTIyNzk0fQ.v7g_DMZsIRRnOorrsNmGV7Tt0cAaczR-cV3vi3s3S74";

    @ApiOperation("生成token")
    @GetMapping("/generateToken")
    public ResponseData generateToken(@RequestParam String username) {
        String token = JwtUtil.generateToken(username);
        return new ResponseData("生成token成功 token = " + token);
    }

    @ApiOperation("根据token获取用户名")
    @GetMapping("/extractUsername")
    public ResponseData extractUsername() {
        String tokenValue = JwtUtil.extractUsername(token);
        return new ResponseData("extractUsername token = " + tokenValue);
    }

    @ApiOperation("根据token获取token有效期")
    @GetMapping("/extractExpiration")
    public ResponseData extractExpiration() {
        Date date = JwtUtil.extractExpiration(token);
        return new ResponseData("extractExpiration token = " + DateUtils.SIMPLE_DATE_FORMAT.format(date));
    }

    @ApiOperation("查看token是否有效")
    @GetMapping("/validateToken")
    public ResponseData validateToken() {
        Boolean aBoolean = JwtUtil.validateToken(token, "18888888888");
        return new ResponseData("validateToken = " + aBoolean);
    }

    @ApiOperation("查看token是否过期")
    @GetMapping("/isTokenExpired")
    public ResponseData isTokenExpired() {
        Boolean aBoolean = JwtUtil.isTokenExpired(token);
        return new ResponseData("isTokenExpired = " + (!aBoolean ? "未过期" : "已过期"));
    }

}
