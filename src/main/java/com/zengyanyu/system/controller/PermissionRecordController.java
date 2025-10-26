package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.PermissionRecord;
import com.zengyanyu.system.query.PermissionRecordQueryObject;
import com.zengyanyu.system.service.IPermissionRecordService;
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
@Api(tags = "权限记录控制器")
@RequestMapping("/permission-record")
@Slf4j
public class PermissionRecordController extends BaseController {

    @Resource
    private IPermissionRecordService permissionRecordService;

    @LogRecord("加载权限记录")
    @ApiOperation("加载权限记录")
    @PostMapping("/loadPermissionRecord")
    public ResponseData loadPermissionRecord() {
        return permissionRecordService.loadPermissionRecord();
    }

    @LogRecord("保存或更新")
    @ApiOperation("保存或更新")
    @PostMapping("/save")
    public ResponseData save(@RequestBody PermissionRecord permissionRecord) {
        return permissionRecordService.saveOrUpdatePermissionRecord(permissionRecord);
    }

    @LogRecord("删除")
    @ApiOperation("删除")
    @GetMapping("/{id}")
    public ResponseData delete(@PathVariable String id) {
        permissionRecordService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除")
    @ApiOperation("批量删除")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        permissionRecordService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<PermissionRecord>> findAll() {
        return new ResponseData("查询所有数据", permissionRecordService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<PermissionRecord> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", permissionRecordService.getById(id));
    }

    @LogRecord("分页查询数据")
    @ApiOperation("分页查询数据")
    @GetMapping("/page")
    public Page<PermissionRecord> page(PermissionRecordQueryObject queryObject) {
        QueryWrapper<PermissionRecord> wrapper = new QueryWrapper<>();
        return permissionRecordService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }

}

