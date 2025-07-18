package com.zengyanyu.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.entity.Menu;
import com.zengyanyu.system.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zengyanyu
 * @since 2025-07-18
 */
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
    @GetMapping("/{id}")
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
        return new ResponseData("根据ID查询指定数据", menuService.list());
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
    public Page<Menu> page(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        return menuService.page(new Page<>(pageNum, pageSize), wrapper);
    }
}

