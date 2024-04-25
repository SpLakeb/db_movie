package com.cinema.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cinema.bean.Type;
import com.cinema.controller.utils.Result;
import com.cinema.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (Type)����Ʋ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@RestController
@RequestMapping("/api/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping
    public Result getAll(){
        return new Result(true,typeService.list());
    }

    @PostMapping
    public  Result save(Type type){
        return new Result(typeService.save(type));
    }

    @PutMapping
    public Result update(Type type){
        return new Result(typeService.modify(type));
    }

    @DeleteMapping("{typeId}")
    public Result delete(@PathVariable Integer typeId){
        return new Result(typeService.delete(typeId));
    }
    @GetMapping("{typeId}")
    public Result getById(@PathVariable Integer typeId){
        return new Result(true,typeService.getById(typeId));
    }


    @GetMapping("/typeList")
    public Result getAllTypes(){

        return new Result(true,typeService.list());
    }

    @GetMapping("/getTypeNameCount")
    public Result getTypeNameCount(){
        List<Type> count = typeService.getTypeNameCount();
        return new Result(!count.isEmpty(), count);
    }

}

