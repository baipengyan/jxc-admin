package com.toesbieya.my.controller;

import com.toesbieya.my.model.entity.SysCategory;
import com.toesbieya.my.model.vo.search.CategorySearch;
import com.toesbieya.my.service.SysCategoryService;
import com.toesbieya.my.utils.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("system/category")
public class SysCategoryController {
    @Resource
    private SysCategoryService categoryService;

    @GetMapping("getAll")
    public Result getAll() {
        return Result.success(categoryService.getAll());
    }

    @PostMapping("search")
    public Result search(@RequestBody CategorySearch vo) {
        return Result.success(categoryService.search(vo));
    }

    @PostMapping("add")
    public Result add(@RequestBody SysCategory category) {
        String errMsg = validateCategoryCreateParam(category);
        if (errMsg != null) return Result.fail(errMsg);

        category.setCtime(System.currentTimeMillis());
        return categoryService.add(category);
    }

    @PostMapping("update")
    public Result update(@RequestBody SysCategory category) {
        String errMsg = validateCategoryUpdateParam(category);
        if (errMsg != null) return Result.fail(errMsg);

        return categoryService.update(category);
    }

    @PostMapping("del")
    public Result del(@RequestBody SysCategory category) {
        if (category.getId() == null) return Result.fail("删除失败，参数错误");
        return categoryService.del(category);
    }

    private String validateCategoryCreateParam(SysCategory category) {
        if (category.getPid() == null) return "创建失败，参数错误";
        if (StringUtils.isEmpty(category.getName())) return "创建失败，分类名称不能为空";
        if (category.getType() == null) return "创建失败，分类类型不能为空";
        return null;
    }

    private String validateCategoryUpdateParam(SysCategory category) {
        if (category.getId() == null) return "修改失败，参数错误";
        if (category.getPid() == null) return "创建失败，参数错误";
        if (StringUtils.isEmpty(category.getName())) return "创建失败，分类名称不能为空";
        if (category.getType() == null) return "创建失败，分类类型不能为空";
        return null;
    }
}
