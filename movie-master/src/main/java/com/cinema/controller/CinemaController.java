package com.cinema.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cinema.bean.Cinema;
import com.cinema.bean.Movie;
import com.cinema.bean.Performer;
import com.cinema.bean.Scenes;
import com.cinema.controller.utils.Result;
import com.cinema.service.CinemaService;
import com.cinema.service.HallService;
import org.springframework.boot.web.reactive.context.ReactiveWebServerApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * (Cinema)����Ʋ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:08
 */
@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    private Random random = new Random();
    private String[] brands = {

           "金逸影城", "万达影城"
            ,"银兴国际影城"
            ,"CGV影城"
            ,"横店影视"
            ,
            "华夏国际影城",
            "中影国际影城",
            "金逸星汇影城",
            "大地影院 ",
            "烽禾影城",
            "银兴电影城",
            "春天国际影城",
            "恒大嘉凯影城",
            "德信影城",
            "橙天嘉禾影城",
            "木槿校园影城" ,
            "华谊兄弟",
            "中影数字国际影城",
            "星美国际影城",
            "中影星美国际影城" ,
            "万象影城" ,
            "幸福蓝海国际影城",
            "卢米埃影城",
            "博纳国际影城" ,
            "星轶starx影城", "百老汇影城", "嘉裕国际影城"
    };

    @Resource
    CinemaService cinemaService;

    @Resource
    HallService hallService;

    @GetMapping("/getCinemaDetails")
    public Result getCinemaDetails(@RequestParam("cinemaId") String cinemaId) {
        Cinema cinema = cinemaService.getById(cinemaId);
        return new Result(cinema != null, cinema);
    }

    /**
     * 获得此影院的所有在映电影
     *
     * @param cinemaId 此影院ID
     * @return Result
     */
    @GetMapping("getCinemaMovie")
    public Result getCinemaMovie(@RequestParam("cinemaId") String cinemaId) {
        List<Movie> cinemas = cinemaService.getAllMoviesInCinema(cinemaId);
        return new Result(cinemas != null, cinemas);
    }

    @GetMapping("getAllScenes")
    public Result getAllScenes(@RequestParam("cinemaId") String cinemaId, @RequestParam("movieId") String movieId) {
        List<Scenes> allScenes = cinemaService.getAllScenes(cinemaId, movieId);
        return new Result(allScenes != null, allScenes);
    }

    @GetMapping("getAllCinemaBrand")
    public Result getAllCinemaBrand() {
        List<String> allBrands = cinemaService.getAllBrands();
        return new Result(allBrands.size() != 0, allBrands);
    }

    @GetMapping("getAllType")
    public Result getAllType() {
        List<String> type = cinemaService.getAllType();
        return new Result(type.size() != 0, type);
    }

    @GetMapping("getAllCinemas")
    public Result getAllCinemas(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                @RequestParam(value = "brand", defaultValue = "全部") String brand,
                                @RequestParam(value = "type",defaultValue = "全部")String type,
                                HttpSession session,
                                int order, String movieId) {

        Page<Cinema> basicPage = new Page<>(page, 8);
        QueryWrapper<Cinema> cinemaQueryWrapper = new QueryWrapper<>();
        cinemaQueryWrapper.select("DISTINCT t_cinema.*");
        System.out.println("-----------------type----------------" + type);
        StringBuilder lastSql = null;
        if (!"全部".equals(type)) {
            lastSql = new StringBuilder("join t_hall h on t_cinema.cinema_id = h.cinema_id and h.type = " + "'" + type + "'");
            if (!"全部".equals(brand)){
                lastSql.append("and t_cinema.brand = " + "'" + brand + "'");
            }
        }else {
            if (!"全部".equals(brand)) {
                cinemaQueryWrapper.eq("brand", brand);
            }
        }

        //判断movieId是否为空
        if (!"empty".equals(movieId)) {

            String sql = " JOIN t_hall ON t_cinema.`cinema_id` = t_hall.`cinema_id`\n" +
                    "JOIN t_scenes ON t_hall.`hall_id` = t_scenes.`hall_id`\n" +
                    "AND t_scenes.`movie_id` = " + "'" + movieId + "'";

            if (lastSql == null) {
                lastSql = new StringBuilder(sql);
            } else {
                lastSql.append(sql);
            }
        }


        if (lastSql != null) {
            cinemaQueryWrapper.last(lastSql.toString());
        }


        //给影院添加距离
        String key = (String) session.getAttribute("key");

        Page<Cinema> cinemaPage = cinemaService.page(basicPage, cinemaQueryWrapper);

        if (order == 1){
            //无需排序
            List<Cinema> cinemaList = cinemaPage.getRecords();

            cinemaService.pushListToRedis(cinemaList);

            for (Cinema cinema : cinemaList) {

                cinema.setDistance(cinemaService.getDistanceByCinemaId(cinema.getCinemaId(), key));
            }

            cinemaPage.setRecords(cinemaList);

        }else if (order == 2){
            //查出所有
            List<Cinema> cinemaList = cinemaService.list(cinemaQueryWrapper);

            cinemaService.pushListToRedis(cinemaList);

            for (Cinema cinema : cinemaList) {

                cinema.setDistance(cinemaService.getDistanceByCinemaId(cinema.getCinemaId(), key));
            }

            Collections.sort(cinemaList, (Cinema o1, Cinema o2)->{
                if (o1.getDistance() - o2.getDistance()>0){
                    return 1;
                }else {
                    return -1;
                }
            });

            cinemaPage.setRecords(cinemaList.subList((page-1)*8,Math.min((page-1)*8+8,
                    cinemaList.size())));

        }else {
           //价格最低
        }

        return new Result(true,cinemaPage);

    }

    @PutMapping("editCinema")
    public Result editPerformer(@RequestParam("cinemaName") String cinemaName,
                                @RequestParam("brand") String brand,
                                @RequestParam("address") String address,
                                @RequestParam("cinemaId") String cinemaId
                                ) {
        Cinema cinema = new Cinema();
        cinema
                .setCinemaId(cinemaId)
                .setCinemaName(cinemaName)
                .setBrand(brand)
                .setAddress(address);
        boolean updatePer = cinemaService.updateById(cinema);

        boolean flag =updatePer;
        return new Result(flag, flag ? "信息修改成功" : "信息修改失败");
    }
    /**
     * 模糊查询所有可能的演员
     */
    @GetMapping("/getAllCinemaBySearch")
    public Result getAllCinemaBySearch(Integer currentPage,Integer pageSize,@RequestParam(required = false) String cinemaKeyword) {
        LambdaQueryWrapper<Cinema> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Cinema::getCinemaName, cinemaKeyword).or().like(Cinema::getAddress, cinemaKeyword).or().like(Cinema::getBrand, cinemaKeyword);
        Page<Cinema> page = cinemaService.page(new Page<>(currentPage, pageSize), lambdaQueryWrapper);
        return new Result(page != null, page);
    }

    @GetMapping("listCinemas/{currentPage}/{pageSize}")
    public Result listCinemas(@PathVariable int currentPage, @PathVariable int pageSize) {
        IPage<Cinema> page = cinemaService.getAllByPage(currentPage, pageSize);
        return new Result(page != null, page);
    }

}
