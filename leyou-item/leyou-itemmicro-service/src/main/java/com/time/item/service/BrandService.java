package com.time.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.time.common.pojo.PageResult;
import com.time.item.mapper.BrandMapper;
import com.time.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 品牌的高级查询
     *
     * @param key 查询关键字
     * @param page 当前在哪一页
     * @param rows 每页展示几条数据
     * @param sortBy 按照哪一个字排序
     * @param desc 升序还是降序
     * @return
     */
    public PageResult<Brand> findBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {

        //构建高级查询的对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //key根据name或者letter模糊查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%")
                    .orEqualTo("letter", key);
        }
        //Integer page, Integer rows 添加分页条件
        PageHelper.startPage(page, rows);

        //String sortBy, Boolean desc 添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        List<Brand> brands = this.brandMapper.selectByExample(example);
        //包装PageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        //返回最终包装后的对象
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }
}
