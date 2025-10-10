package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.UserRole;
import com.zengyanyu.system.service.IUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2025-07-18
 */
@RestController
@Api(tags = "用户关联的角色控制器")
@RequestMapping("/user-role")
@Slf4j
public class UserRoleController extends BaseController {

    @Resource
    private IUserRoleService userRoleService;

    @LogRecord("保存或更新用户关联的角色")
    @ApiOperation("保存或更新用户关联的角色")
    @PostMapping("/save")
    public ResponseData save(@RequestBody UserRole userRole) {
        return userRoleService.saveOrUpdateUserRole(userRole);
    }

    @LogRecord("删除用户关联的角色")
    @ApiOperation("删除用户关联的角色")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        userRoleService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除用户关联的角色")
    @ApiOperation("批量删除用户关联的角色")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        userRoleService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<UserRole>> findAll() {
        return new ResponseData("根据ID查询指定数据", userRoleService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<UserRole> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", userRoleService.getById(id));
    }

    @LogRecord("用户关联的角色分页查询数据")
    @ApiOperation("用户关联的角色分页查询数据")
    @GetMapping("/page")
    public Page<UserRole> page(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        return userRoleService.page(new Page<>(pageNum, pageSize), wrapper);
    }
}

