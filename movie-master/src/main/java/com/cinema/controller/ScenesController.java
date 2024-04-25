package com.cinema.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cinema.bean.Performer;
import com.cinema.bean.Scenes;
import com.cinema.bean.*;
import com.cinema.controller.utils.Result;
import com.cinema.service.CinemaService;
import com.cinema.service.HallService;
import com.cinema.service.MovieService;
import com.cinema.service.ScenesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;
import java.io.IOException;

/**
 * 电影场次表(Scenes)����Ʋ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@RestController
@RequestMapping("/api/scenes")
public class ScenesController {
    @Resource
    ScenesService scenesService;

    @Resource
    private MovieService movieService;

    @Resource
    private HallService hallService;

    @Resource
    private CinemaService cinemaService;


    @GetMapping("/getAllByMovieId")
    public Result getAllByMovieId(@RequestParam("movieId") String movieId){
        List<Scenes> scenes =scenesService.getAllByMovieId(movieId);
        return new Result(scenes!=null,scenes);
    }

    @GetMapping("/getAllByHallId/{hallId}")
    public Result getAllByCinemaIdAndHallId(@PathVariable String hallId){
        List<Scenes> scenes=scenesService.getAllByHallId(hallId);
        return  new Result(scenes!=null,scenes);
    }

    @GetMapping("/getScenesByDate/{hallId}/{date}")
    public Result getScenesByDate(@PathVariable String hallId, @PathVariable Integer date) {
        System.out.println(date);
        List<Scenes> scenesList = scenesService.getAllByHallId(hallId);
        List<Scenes> result = new ArrayList<>();
        for (Scenes scenes : scenesList) {
            if (scenes.getDateStart().getDate() == date) {
                result.add(scenes);
            }
        }
        return new Result(true, result);
    }

    @PutMapping("editScenes")
    public Result editScenes(@RequestParam("movieId") String movieId,
                             @RequestParam("scenesId") String scenesId,
                             @RequestParam("dateStart") Date dateStart,
                             @RequestParam("dateEnd") Date dateEnd,
                             @RequestParam("price") Double price) {
        Scenes scenes = new Scenes();

        scenes.setMovieId(movieId)
                .setScenesId(scenesId)
                .setDateStart(dateStart)
                .setDateEnd(dateEnd)
                .setPrice(price);
        boolean updatePer = scenesService.updateById(scenes);


        boolean flag = updatePer;
        return new Result(flag, flag ? "信息修改成功" : "信息修改失败");
    }
    @GetMapping("/getTotalPrice")
    public Result getTotalPrice() {
        List<Scenes> totalPrice = scenesService.getTotalPrice();
        return new Result(!totalPrice.isEmpty(), totalPrice);
    }
    @GetMapping("/getScenesInfo")
    public Result getScenesInfo(String scenesId){

        Map<String, Object> map = new HashMap<>(5);

        Scenes scenes = scenesService.getById(scenesId);

        Movie movie = movieService.getById(scenes.getMovieId());

        Hall hall = hallService.getById(scenes.getHallId());

        Cinema cinema = cinemaService.getById(hall.getCinemaId());

        List<Type> types = movieService.movieType(movie.getMovieId());

        map.put("scenes",scenes);
        map.put("movie",movie);
        map.put("hall",hall);
        map.put("cinema",cinema);
        map.put("types",types);

        return new Result(true,map);

    }

    @PostMapping("/addScenes")
    public Result addScenes(@RequestBody Scenes scenes) {
        boolean flag = scenesService.save(scenes);
        return new Result(flag, flag ? "添加成功":"添加失败");
    }

    @DeleteMapping("/deleteByScenesId/{scenesId}")
    public Result deleteByScenesId(@PathVariable String scenesId) {
        boolean flag = scenesService.removeById(scenesId);
        return new Result(flag, flag ? "删除成功" : "删除失败");
    }
}

