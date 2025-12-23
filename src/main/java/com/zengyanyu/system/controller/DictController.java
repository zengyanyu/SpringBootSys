package com.zengyanyu.system.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.dto.DictExportExcelDto;
import com.zengyanyu.system.entity.Dict;
import com.zengyanyu.system.query.DictQueryObject;
import com.zengyanyu.system.service.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2025-07-18
 */
@Slf4j
@RestController
@Api(tags = "数据字典控制器")
@RequestMapping("/dict")
public class DictController extends BaseController {

    @Resource
    private IDictService dictService;

    @LogRecord("保存或更新数据字典")
    @ApiOperation("保存或更新数据字典")
    @PostMapping("/save")
    public ResponseData save(@RequestBody Dict dict) {
        return dictService.saveOrUpdateDict(dict);
    }

    @LogRecord("删除数据字典")
    @ApiOperation("删除数据字典")
    @GetMapping("/del/{id}")
    public ResponseData delete(@PathVariable String id) {
        dictService.removeById(id);
        return new ResponseData("删除成功");
    }

    @LogRecord("批量删除数据字典")
    @ApiOperation("批量删除数据字典")
    @PostMapping("/del/batch")
    public ResponseData deleteBatch(@RequestBody List<String> ids) {
        dictService.removeByIds(ids);
        return new ResponseData("批量删除成功");
    }

    @LogRecord("查询所有数据")
    @ApiOperation("查询所有数据")
    @GetMapping("/findAll")
    public ResponseData<List<Dict>> findAll() {
        return new ResponseData("查询所有数据", dictService.list());
    }

    @LogRecord("根据ID查询指定数据")
    @ApiOperation("根据ID查询指定数据")
    @GetMapping("/get/{id}")
    public ResponseData<Dict> get(@PathVariable String id) {
        return new ResponseData("根据ID查询指定数据", dictService.getById(id));
    }

    @LogRecord("数据字典分页查询数据")
    @ApiOperation("数据字典分页查询数据")
    @GetMapping("/page")
    public Page<Dict> page(DictQueryObject queryObject) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        return dictService.page(new Page<>(queryObject.getPageNum(), queryObject.getPageSize()), wrapper);
    }

    @LogRecord("导出Excel文件")
    @ApiOperation("导出Excel文件")
    @PostMapping("/exportExcel")
    public void exportExcel() throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        String fileName = URLEncoder.encode("字典列表", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 模拟测试数据
        List<DictExportExcelDto> dtoList = new ArrayList<>();
        List<Dict> dictList = dictService.list();
        for (Dict dict : dictList) {
            // 创建对象
            DictExportExcelDto dto = new DictExportExcelDto();
            BeanUtils.copyProperties(dict, dto);
            dto.setExportDate(new Date());
            dtoList.add(dto);
        }
        EasyExcel.write(response.getOutputStream(), DictExportExcelDto.class)
                .sheet("字典列表").doWrite(dtoList);
    }

    @LogRecord("导入Excel文件")
    @ApiOperation("导入Excel文件")
    @PostMapping("/importExcel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        // 校验文件是否为空
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请选择上传的Excel文件");
        }
        // 自动关闭资源
        try (InputStream inputStream = file.getInputStream()) {
            // 导入Excel文件
            dictService.importExcel(inputStream);
            return ResponseEntity.ok("Excel 导入成功,共处理" + file.getSize() + "字节数据");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok("Excel 导入失败,失败原因: " + e.getMessage());
        }
    }

}

