package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: leyou
 * @description: 商品分类的Controller
 * @author: Mr.D
 * @create: 2019-11-07 16:21
 **/
@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * 根据父节点的id查询子节点
     *
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(value = "pid", defaultValue = "0") Long pid) {

        if (pid == null || pid < 0) {
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            //400: 参数不合法
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = categoryService.queryCategoryByPid(pid);

        //404:资源未找到
        if (CollectionUtils.isEmpty(categories)) {
            return ResponseEntity.notFound().build();
        }
        //200: 查询成功
        return ResponseEntity.ok(categories);
    }
}
