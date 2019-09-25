package com.time.item.service;

import com.time.item.mapper.CategoryMapper;
import com.time.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父节点查询子节点
     * @param pid
     * @return
     */
    public List<Category> findCategoryByPid(Long pid) {
        Category record = new Category();
        record.setIsParentId(pid);
        return this.categoryMapper.select(record);
    }
}
