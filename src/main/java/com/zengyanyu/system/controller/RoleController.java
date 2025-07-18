package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.Role;
import com.zengyanyu.system.query.RoleQueryObject;
import com.zengyanyu.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2024-11-19
 */
@RestController
@Api(tags = "角色控制器")
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Resource
    private IRoleService roleService;

    @LogRecord("角色保存或更新")
    @ApiOperation("角色保存或更新")
    @PostMapping("/save")
    public ResponseData save(@RequestBody Role role) {
        return roleService.saveOrUpdateRole(role);
    }

    @LogRecord("角色删除")
    @ApiOperation("角色删除")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        roleService.removeById(id);
        return new ResponseData("删除角色成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<Role>> findAll() {
        return new ResponseData("查询所有数据成功", roleService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<Role> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据成功", roleService.getById(id));
    }

    @LogRecord("角色分页查询数据")
    @ApiOperation("角色分页查询数据")
    @GetMapping("/page")
    public Page<Role> page(RoleQueryObject queryObject) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("role_code");
        if (StringUtils.isNotEmpty(queryObject.getRoleName())) {
            wrapper.like("role_name", queryObject.getRoleName());
        }
        return roleService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }
}

