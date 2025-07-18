package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.Menu;
import com.zengyanyu.system.mapper.MenuMapper;
import com.zengyanyu.system.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 菜单 服务实现类
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    /**
     * 保存或更新菜单
     *
     * @param menu 菜单
     * @return
     */
    @Override
    public ResponseData saveOrUpdateMenu(Menu menu) {
        this.saveOrUpdate(menu);
        return new ResponseData("保存或更新成功");
    }

}
