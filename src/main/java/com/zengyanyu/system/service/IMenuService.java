package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 菜单 服务类
 *
 * @author zengyanyu
 * @since 2025-07-18
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 保存或更新菜单
     *
     * @param menu 菜单
     * @return
     */
    ResponseData saveOrUpdateMenu(Menu menu);

}
