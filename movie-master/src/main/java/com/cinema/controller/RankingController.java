package com.cinema.controller;

import com.alibaba.druid.sql.builder.SQLBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cinema.bean.Movie;
import com.cinema.controller.utils.Result;
import com.cinema.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 28794
 * @ClassName RankingController.java
 * @since 2022-03-02 18:32
 */
@RestController
@RequestMapping("/api/rank")
public class RankingController {

    @Resource
    MovieService movieService;

    @GetMapping("/getTop")
    public Result getTop10() {
        QueryWrapper<Movie> movieQueryWrapper = new QueryWrapper<>();
        movieQueryWrapper.lt("release_time", new Date());
        movieQueryWrapper.gt("end_time", new Date());
        String sql = "ORDER BY ROUND(score,2) desc LIMIT 0,10";
        movieQueryWrapper.last(sql);
        List<Movie> list = movieService.list(movieQueryWrapper);
        return new Result(list.size() != 0, list);
    }

    @GetMapping("/getExcepted")
    public Result getExpectedTop() {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("release_time", new Date());
        queryWrapper.orderByDesc("expect");
        queryWrapper.last("limit 0,10");
        List<Movie> movies = movieService.list(queryWrapper);
        return new Result(movies.size() != 0, movies);
    }

    @GetMapping("/getBoxTopMovie")
    public Result getBoxTopMovie() {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("end_time", new Date());
        queryWrapper.lt("release_time", new Date());
        queryWrapper.orderByDesc("box_office");
        queryWrapper.last("limit 0,10");
        List<Movie> movies = movieService.list(queryWrapper);
        return new Result(movies.size() != 0, movies);
    }
}
