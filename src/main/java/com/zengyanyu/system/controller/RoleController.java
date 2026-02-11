package com.zengyanyu.system.controller;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.dto.RoleExportExcelDto;
import com.zengyanyu.system.entity.Role;
import com.zengyanyu.system.framework.strategy.CustomColumnWidthStyleStrategy;
import com.zengyanyu.system.query.RoleQueryObject;
import com.zengyanyu.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2024-11-19
 */
@Slf4j
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
    @GetMapping("/del/{id}")
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

    @LogRecord("导出Excel文件")
    @ApiOperation("导出Excel文件")
    @PostMapping("/exportExcel")
    public void exportExcel() throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String fileName = URLEncoder.encode("角色列表", StandardCharsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        // 模拟测试数据
        List<RoleExportExcelDto> dtoList = new ArrayList<>();
        List<Role> roleList = roleService.list();
        for (Role role : roleList) {
            // 创建对象
            RoleExportExcelDto dto = new RoleExportExcelDto();
            BeanUtils.copyProperties(role, dto);
            dtoList.add(dto);
        }
        EasyExcel.write(response.getOutputStream(), RoleExportExcelDto.class)
                .registerWriteHandler(new CustomColumnWidthStyleStrategy())
                .sheet("角色列表").doWrite(dtoList);
    }

    @LogRecord("导入Excel文件")
    @ApiOperation("导入Excel文件")
    @PostMapping("/importExcel")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("请选择上传的Excel文件！");
        }
        // 自动关闭资源
        try (InputStream inputStream = file.getInputStream()) {
            roleService.importExcel(inputStream);
            return ResponseEntity.ok("Excel文件导入成功！");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Excel文件导入失败：" + ExceptionUtil.stacktraceToString(e));
        }
    }

}

