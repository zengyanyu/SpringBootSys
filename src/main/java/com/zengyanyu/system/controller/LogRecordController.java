/*
 * Copyright (c) 2026, 曾衍育 All rights reserved.
 * 自定义License声明
 * ZENGYANYU PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zengyanyu.system.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.dto.LogRecordExportExcelDto;
import com.zengyanyu.system.entity.LogRecordEntity;
import com.zengyanyu.system.framework.strategy.CustomColumnWidthStyleStrategy;
import com.zengyanyu.system.query.LogRecordQueryObject;
import com.zengyanyu.system.service.ILogRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2025-07-16
 */
@RestController
@Api(tags = "日志记录控制器")
@RequestMapping("/log-record")
public class LogRecordController extends BaseController {

    @Resource
    private ILogRecordService logRecordEntityService;

    @ApiOperation("日志记录分页查询数据")
    @GetMapping("/page")
    public Page<LogRecordEntity> page(LogRecordQueryObject queryObject) {
        QueryWrapper<LogRecordEntity> wrapper = new QueryWrapper<>();
        // 操作名称
        if (StringUtils.isNotEmpty(queryObject.getOperateName())) {
            wrapper.like("operate_name", queryObject.getOperateName());
        }
        // 请求时间
        if (null != queryObject.getStartTime() && StringUtils.isNotEmpty(queryObject.getStartTime())
                && null != queryObject.getEndTime() && StringUtils.isNotEmpty(queryObject.getEndTime())) {
            wrapper.between("request_time", queryObject.getStartTime(), queryObject.getEndTime());
        }
        return logRecordEntityService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }

    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<LogRecordEntity>> findAll() {
        return new ResponseData("查询所有数据", logRecordEntityService.list());
    }

    @ApiOperation("导出Excel文件")
    @PostMapping("/exportExcel")
    public void exportExcel() throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String fileName = URLEncoder.encode("日志记录列表", StandardCharsets.UTF_8.name());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".xlsx");

        // 模拟测试数据
        List<LogRecordExportExcelDto> logRecordDtoList = new ArrayList<>();
        List<LogRecordEntity> logRecordEntityList = logRecordEntityService.list();
        for (LogRecordEntity logRecordEntity : logRecordEntityList) {
            // 创建对象
            LogRecordExportExcelDto logRecordDto = new LogRecordExportExcelDto();
            BeanUtils.copyProperties(logRecordEntity, logRecordDto);
            // add
            logRecordDtoList.add(logRecordDto);
        }
        EasyExcel.write(response.getOutputStream(), LogRecordExportExcelDto.class)
                .registerWriteHandler(new CustomColumnWidthStyleStrategy())
                .sheet("日志记录列表").doWrite(logRecordDtoList);
    }

}

