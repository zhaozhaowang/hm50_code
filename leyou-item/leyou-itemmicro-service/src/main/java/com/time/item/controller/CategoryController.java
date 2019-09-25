package com.time.item.controller;

import com.time.item.pojo.Category;
import com.time.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 根据父节点的id查询子节点
     * @param pid
     * @return
     */
    //http://api.leyou.com/api/item/category/list?pid=0
    @GetMapping("/list")
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid) {

        if (pid == null || pid < 0) {
            //响应400参数不合法
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = this.categoryService.findCategoryByPid(pid);

        //判断集合是否为空
        if (CollectionUtils.isEmpty(categories)) {
            //资源服务器未响应
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categories);
    }
}

