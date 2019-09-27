package com.time.item.controller;

import com.time.common.pojo.PageResult;
import com.time.item.pojo.Brand;
import com.time.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/brand")
public class BrandController {


    @Autowired
    private BrandService brandService;

    //http://api.leyou.com/api/item/brand/page?key=&page=1&rows=5&sortBy=id&desc=false

    /**
     * 高级查询
     *
     * @param key    查询关键字
     * @param page   默认在第1页
     * @param rows   默认一页展示五条数据
     * @param sortBy 默认不按照某一个字段排序
     * @param desc   默认不清楚升序或者降序
     * @return
     */
    @GetMapping("/page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", required = false) Boolean desc
    ) {
        //list分页查询的结果集
        PageResult<Brand> list = this.brandService.findBrandByPage(key, page, rows, sortBy, desc);
        if (list == null || CollectionUtils.isEmpty(list.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }


    //保存品牌数据
    //@RequestBody Brand brand接受前台传来的json对象
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, List<Long> cids) {
        this.brandService.toSaveBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
