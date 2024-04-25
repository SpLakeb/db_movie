package com.cinema.controller;


import com.cinema.bean.Hall;
import com.cinema.bean.Type;
import com.cinema.controller.utils.Result;
import com.cinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Hall)����Ʋ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@RestController
@RequestMapping("/api/hall")
public class HallController {

    @Resource
    HallService hallService;

    @GetMapping("/getByCinemaId")
    public Result getByCinemaId(@RequestParam("cinemaId") String cinemaId){
        List<Hall> halls = hallService.getByCinemaId(cinemaId);
        return new Result(halls!=null,halls);

    }

    @GetMapping("/getAllByCinemaId/{cinemaId}")
    public Result getAllByCinemaId(@PathVariable String cinemaId){
        List<Hall> halls = hallService.getAllByCinemaId(cinemaId);
        return new Result(halls!=null,halls);
    }

    @GetMapping("/getAllByHallId")
    public Result getAllByHallId(@RequestParam("hallId") String hallId){
        List<Hall> halls = hallService.getAllByHallId(hallId);
        return  new Result(halls!=null,halls);
    }

    @GetMapping("/getAllByType")
    public  Result getAllByType(@RequestParam("type") String type){
        List<Hall> halls = hallService.getAllByType(type);
        return  new Result(halls!=null,halls);
    }

}

