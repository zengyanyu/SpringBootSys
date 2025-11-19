package com.zengyanyu.system.service;

import com.zengyanyu.system.entity.AlgorithmDataLanguage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 算法编程语言 服务类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
public interface IAlgorithmDataLanguageService extends IService<AlgorithmDataLanguage> {

    /**
     * 保存或更新算法编程语言
     *
     * @param algorithmDataLanguage 算法编程语言
     * @return
     */
    ResponseData saveOrUpdateAlgorithmDataLanguage(AlgorithmDataLanguage algorithmDataLanguage);

}
