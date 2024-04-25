package com.cinema.controller;


import com.cinema.bean.Movietype;
import com.cinema.controller.utils.Result;
import com.cinema.service.MovietypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (Movietype)����Ʋ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@RestController
@RequestMapping("/api/movietype")
public class MovietypeController {
    @Autowired
    private MovietypeService movietypeService;

    @GetMapping
    public Result getAll(){
        return new Result(true,movietypeService.list());
    }
    @PostMapping
    public Result save(Movietype movietype){
        return  new Result(movietypeService.save(movietype));
    }

    @PutMapping
    public Result update(Movietype movietype){
        return new Result(movietypeService.modify(movietype));
    }
    @GetMapping("{movieId}")
    public Result getById(@PathVariable Integer movieId){
        return new Result(true,movietypeService.getById(movieId));
    }

    @PostMapping("/saveMovieTypes")
    public Result saveMovieTypes(@RequestBody List<Movietype> movieTypeList) {
        boolean flag = movietypeService.saveBatch(movieTypeList);
        return new Result(flag);
    }

    @PutMapping("/modifyMovieTypes/{movieId}")
    public Result modifyMovieTypes(@RequestBody List<Movietype> movieTypeList, @PathVariable String movieId) {
        Boolean delete = movietypeService.delete(movieId);
        Boolean update = movietypeService.saveBatch(movieTypeList);
        return new Result(delete && update, delete && update ? "类型修改成功！" : "类型修改失败！");
    }

}

