package com.zengyanyu.system.controller;

import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.dto.UserInfoDto;
import com.zengyanyu.system.entity.UserInfo;
import com.zengyanyu.system.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2024-11-18
 */
@Slf4j
@RestController
@Api(tags = "用户信息控制器")
@RequestMapping("/user-info")
public class UserInfoController extends BaseController {

    @Resource
    private IUserInfoService userInfoService;

    @LogRecord("用户保存或更新")
    @ApiOperation("用户保存或更新")
    @PostMapping("/save")
    public ResponseData save(@RequestBody UserInfoDto userInfo) {
        return userInfoService.saveOrUpdateUserInfo(userInfo);
    }

    @LogRecord("用户登录")
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ResponseData login(@RequestBody UserInfoDto userInfoDto) {
        return userInfoService.login(userInfoDto);
    }

    @LogRecord("获取用户信息")
    @ApiOperation("获取用户信息")
    @GetMapping("/userInfo")
    public ResponseData userInfo() {
        // 从请求头中获取凭证
        String authorization = request.getHeader("authorization");
        return userInfoService.userInfo(authorization.split(" ")[1]);
    }

    @LogRecord("用户退出")
    @ApiOperation("用户退出")
    @GetMapping("/logout")
    public ResponseData logout() {
        // 从请求头中获取凭证
        String authorization = request.getHeader("authorization");
        return userInfoService.logout(authorization.split(" ")[1]);
    }

    @LogRecord("删除用户")
    @ApiOperation("删除用户")
    @GetMapping("/del/{id}")
    public ResponseData delete(@PathVariable String id) {
        userInfoService.removeById(id);
        return new ResponseData("删除用户成功");
    }

    @LogRecord("批量删除")
    @ApiOperation("批量删除")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        userInfoService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<UserInfo>> findAll() {
        return new ResponseData("查询所有数据", userInfoService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<UserInfo> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", userInfoService.getById(id));
    }

    @LogRecord("是否锁定修改状态")
    @ApiOperation("是否锁定修改状态")
    @PostMapping("/isLockUpdate")
    public ResponseData isLockUpdate(@RequestBody UserInfoDto userInfo) {
        return userInfoService.isLockUpdate(userInfo);
    }

    @LogRecord("重置密码")
    @ApiOperation("重置密码")
    @PostMapping("/resetPassword")
    public ResponseData resetPassword(@RequestBody UserInfoDto userInfo) {
        return userInfoService.resetPassword(userInfo);
    }

}

