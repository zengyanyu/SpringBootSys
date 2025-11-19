package com.zengyanyu.system.service.impl;

import com.zengyanyu.system.entity.AlgorithmDataLanguage;
import com.zengyanyu.system.mapper.AlgorithmDataLanguageMapper;
import com.zengyanyu.system.service.IAlgorithmDataLanguageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.zengyanyu.system.commons.ResponseData;

/**
 * 算法编程语言 服务实现类
 *
 * @author zengyanyu
 * @since 2025-11-19
 */
@Service
public class AlgorithmDataLanguageServiceImpl extends ServiceImpl<AlgorithmDataLanguageMapper, AlgorithmDataLanguage> implements IAlgorithmDataLanguageService {

    /**
     * 保存或更新算法编程语言
     *
     * @param algorithmDataLanguage 算法编程语言
     * @return
     */
    @Override
    public ResponseData saveOrUpdateAlgorithmDataLanguage(AlgorithmDataLanguage algorithmDataLanguage) {
        this.saveOrUpdate(algorithmDataLanguage);
        return new ResponseData("保存或更新成功");
    }

}
