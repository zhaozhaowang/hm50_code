package com.time.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.time.common.pojo.PageResult;
import com.time.item.mapper.BrandMapper;
import com.time.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 品牌的高级查询
     *
     * @param key    查询关键字
     * @param page   当前在哪一页
     * @param rows   每页展示几条数据
     * @param sortBy 按照哪一个字排序
     * @param desc   升序还是降序
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


    /**
     * 如果你一次执行单条查询语句，则没有必要启用事务支持，数据库默认支持SQL执行期间的读一致性；
     如果你一次执行多条查询语句，例如统计查询，报表查询，在这种场景下，多条查询SQL必须保证整体的读一致性，
     否则，在前条SQL查询之后，后条SQL查询之前，数据被其他用户改变，则该次整体的统计查询将会出现读数据不一致的状态，
     此时，应该启用事务支持。
     */
    /**
     * 新增品牌
     * @param brand
     * @param cids  这里为什么需要加Transactional注解呢?
     *              DataSourceTransactionManager
     *              多表的操作要用到事务,那么单个表的操作
     */

    @Transactional
    public void toSaveBrand(Brand brand, List<Long> cids) {
        //先新增brand这个表
        this.brandMapper.insertSelective(brand);

        //再去新增中间表
        //cids.forEach(cid -> {
        //通用 mapper 解决的是单表的增删改查问题。不过通用 mapper 依然支持普通的 xml 映射语句，不冲突。
        //复杂查询手写吧，避免不了。通用 mapper 是节省重复工作的，复杂查询一般不是重复性的。
        //    Long b_id = brand.getId();
        //    this.brandMapper.insertCategoryAndBrand(cid, b_id);
        //});


        //因为我们在模型上设置的是,
        // @Id
        //@GeneratedValue(strategy = GenerationType.IDENTITY)
        //所以brand对象的id是自增的,并且插入成功后会将id返给我们

        Long brand_id = brand.getId();
        System.out.println("joyce  " + brand_id + " name " + brand.getName());
        System.out.println("joyce cids size " + cids.size());
        for (Long categoryId : cids) {
            System.out.println("joyce  cid " + categoryId);
            this.brandMapper.insertCategoryAndBrand(categoryId, brand_id);
        }

    }
}
