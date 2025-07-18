package com.zengyanyu.system.util;

import com.zengyanyu.system.dto.CategoryDetailDto;
import com.zengyanyu.system.dto.CategoryDetailTreeDto;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CategorySortUtil {

    /**
     * 根据一级分类的listOrder进行升序排序
     *
     * @param data 需要排序的列表
     */
    public static void sortCategoryDetailTreeDtoByListOrder(List<CategoryDetailTreeDto> data) {
        if (data == null || data.isEmpty()) {
            return;
        }
        // 对一级分类按listOrder升序排序
        Collections.sort(data, Comparator.comparingLong(CategoryDetailTreeDto::getListOrder));
        // 递归处理子分类（如果需要）
        for (CategoryDetailTreeDto category : data) {
            if (category.getChildren() != null && !category.getChildren().isEmpty()) {
                sortCategoryDetailTreeDtoByListOrder(category.getChildren());
            }
        }
    }

    /**
     * 根据一级分类的listOrder进行升序排序
     *
     * @param data 需要排序的列表
     */
    public static void sortCategoryDetailDtoByListOrder(List<CategoryDetailDto> data) {
        if (data == null || data.isEmpty()) {
            return;
        }
        // 对一级分类按listOrder升序排序
        Collections.sort(data, Comparator.comparingLong(CategoryDetailDto::getListOrder));
        // 递归处理子分类（如果需要）
        for (CategoryDetailDto category : data) {
            if (category.getCategoryDetailDtos() != null && !category.getCategoryDetailDtos().isEmpty()) {
                sortCategoryDetailDtoByListOrder(category.getCategoryDetailDtos());
            }
        }
    }

}
