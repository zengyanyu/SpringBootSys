package com.zengyanyu.system.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.CustomColumnWidthStyleStrategy;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.dto.PermissionRecordExportExcelDto;
import com.zengyanyu.system.entity.PermissionRecord;
import com.zengyanyu.system.query.PermissionRecordQueryObject;
import com.zengyanyu.system.service.IPermissionRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2025-07-18
 */
@Slf4j
@RestController
@Api(tags = "权限记录控制器")
@RequestMapping("/permission-record")
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
    @GetMapping("/del/{id}")
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

    @LogRecord("导出Excel文件")
    @ApiOperation("导出Excel文件")
    @PostMapping("/exportExcel")
    public void exportExcel() throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String fileName = URLEncoder.encode("权限记录列表", StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 模拟测试数据
        List<PermissionRecordExportExcelDto> dtoList = new ArrayList<>();
        List<PermissionRecord> permissionRecordList = permissionRecordService.list();
        for (PermissionRecord permissionRecord : permissionRecordList) {
            // 创建对象
            PermissionRecordExportExcelDto dto = new PermissionRecordExportExcelDto();
            BeanUtils.copyProperties(permissionRecord, dto);
            dtoList.add(dto);
        }
        EasyExcel.write(response.getOutputStream(), PermissionRecordExportExcelDto.class)
                .registerWriteHandler(new CustomColumnWidthStyleStrategy())
                .sheet("权限记录列表").doWrite(dtoList);
    }

}

