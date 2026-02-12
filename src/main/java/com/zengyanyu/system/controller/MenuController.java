package com.zengyanyu.system.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.dto.MenuExportExcelDto;
import com.zengyanyu.system.entity.Menu;
import com.zengyanyu.system.framework.strategy.CustomColumnWidthStyleStrategy;
import com.zengyanyu.system.query.MenuQueryObject;
import com.zengyanyu.system.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
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
@Api(tags = "菜单控制器")
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Resource
    private IMenuService menuService;

    @LogRecord("保存或更新菜单")
    @ApiOperation("保存或更新菜单")
    @PostMapping("/save")
    public ResponseData save(@RequestBody Menu menu) {
        return menuService.saveOrUpdateMenu(menu);
    }

    @LogRecord("删除菜单")
    @ApiOperation("删除菜单")
    @GetMapping("/del/{id}")
    public ResponseData delete(@PathVariable String id) {
        menuService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除菜单")
    @ApiOperation("批量删除菜单")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        menuService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<Menu>> findAll() {
        return new ResponseData("查询所有数据", menuService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<Menu> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", menuService.getById(id));
    }

    @LogRecord("菜单分页查询数据")
    @ApiOperation("菜单分页查询数据")
    @GetMapping("/page")
    public Page<Menu> page(MenuQueryObject queryObject) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(queryObject.getName())) {
            wrapper.like("name", queryObject.getName());
        }
        return menuService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }

    @LogRecord("导出Excel文件")
    @ApiOperation("导出Excel文件")
    @PostMapping("/exportExcel")
    public void exportExcel() throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String fileName = URLEncoder.encode("菜单列表", StandardCharsets.UTF_8.name());
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".xlsx");

        // 模拟测试数据
        List<MenuExportExcelDto> dtoList = new ArrayList<>();
        List<Menu> roleList = menuService.list();
        for (Menu menu : roleList) {
            // 创建对象
            MenuExportExcelDto dto = new MenuExportExcelDto();
            BeanUtils.copyProperties(menu, dto);
            dtoList.add(dto);
        }
        EasyExcel.write(response.getOutputStream(), MenuExportExcelDto.class)
                .registerWriteHandler(new CustomColumnWidthStyleStrategy())
                .sheet("菜单列表").doWrite(dtoList);
    }

}

