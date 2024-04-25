package com.cinema.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cinema.bean.Cinema;
import com.cinema.bean.Movie;
import com.cinema.bean.Performer;
import com.cinema.controller.utils.Result;
import com.cinema.service.CinemaService;
import com.cinema.service.MovieService;
import com.cinema.service.PerformerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 28794
 * @ClassName SearchController.java
 * @since 2022-02-23 16:24
 */
@RestController
@RequestMapping("/api/search")
@Slf4j
public class SearchController {

    @Resource
    private MovieService movieService;

    @Resource
    private PerformerService performerService;

    @Resource
    private CinemaService cinemaService;

    /**
     * 通过影视剧、影人、影院来查询相关信息
     */
    @GetMapping("/getSuggestions")
    public Result fuzzyQuery(@RequestParam("keyWord") String keyWord) throws NullPointerException {
        keyWord = "%" + keyWord + "%";
        List<Movie> movies = null;
        List<Performer> performers = null;
        List<Cinema> cinemas = null;
        try {
            movies = movieService.getSuggestionsMovie(keyWord);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            cinemas = cinemaService.getSuggestionsCinema(keyWord);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        try {
            performers = performerService.getSuggestionsPerformer(keyWord);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        HashMap<String, List<Object>> map = new HashMap<>();
        if (movies != null) {
            map.put("movie", Collections.singletonList(movies));
        }
        if (performers != null) {
            map.put("performer", Collections.singletonList(performers));
        }
        if (cinemas != null) {
            map.put("cinema", Collections.singletonList(cinemas));
        }
        return new Result(!map.isEmpty(), map);
    }

    @GetMapping("/getMovieNumber")
    public Result getMovieNumber(String keyWord) {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("movie_name", keyWord);
        int count = movieService.count(queryWrapper);
        return new Result(count != 0, count);
    }

    @GetMapping("/getCinemaNumber")
    public Result getCinemaNumber(String keyWord) {
        QueryWrapper<Cinema> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("cinema_name", keyWord);
        int count = cinemaService.count(queryWrapper);
        return new Result(count != 0, count);
    }

    @GetMapping("/getPerformerNumber")
    public Result getPerformerNumber(String keyWord) {
        QueryWrapper<Performer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", keyWord);
        int count = performerService.count(queryWrapper);
        System.out.println(" 条:" + count);
        return new Result(count != 0, count);
    }
}
