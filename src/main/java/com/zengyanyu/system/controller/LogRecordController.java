package com.zengyanyu.system.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.framework.strategy.CustomColumnWidthStyleStrategy;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.dto.LogRecordExportExcelDto;
import com.zengyanyu.system.entity.LogRecordEntity;
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
@RequestMapping("/log-record-entity")
public class LogRecordController extends BaseController {

    @Resource
    private ILogRecordService logRecordEntityService;

    @LogRecord("日志记录分页查询数据")
    @ApiOperation("日志记录分页查询数据")
    @GetMapping("/page")
    public Page<LogRecordEntity> page(LogRecordQueryObject queryObject) {
        QueryWrapper<LogRecordEntity> wrapper = new QueryWrapper<>();
//        if (null != queryObject.getStartTime() && null != queryObject.getEndTime()) {
//            wrapper.between("", queryObject.getStartTime(), queryObject.getEndTime());
//        }
        if (StringUtils.isNotEmpty(queryObject.getOperateUsername())) {
            wrapper.like("operate_username", queryObject.getOperateUsername());
        }
        return logRecordEntityService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
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

