package com.cinema.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cinema.bean.Movie;
import com.cinema.bean.Performer;
import com.cinema.controller.utils.Result;
import com.cinema.mapper.PerformerMapper;
import com.cinema.service.MovieService;
import com.cinema.service.PerformerService;
import com.cinema.service.RoleService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Performer)����Ʋ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@RestController
@RequestMapping("/api/performer")
@Slf4j
public class PerformerController {
    @Resource
    PerformerService performerService;

    /**
     * 演员详情
     *
     * @param performerId
     * @return
     */
    @GetMapping("performerDetails")
    public Result performerDetails(@RequestParam("performerId") String performerId) {
        QueryWrapper<Performer> performerQueryWrapper = new QueryWrapper<>();
        performerQueryWrapper.eq("performer_id", performerId);
        Performer performer = performerService.getOne(performerQueryWrapper);
        return new Result(performer != null, performer);
    }

    @GetMapping("getBoxOffice")
    public Result getBoxOffice(@RequestParam("performerId") String performerId) {
        PerformerMapper baseMapper = (PerformerMapper) performerService.getBaseMapper();
        Long boxOffice = baseMapper.getBoxOffice(performerId);
        return new Result(boxOffice != 0, boxOffice);
    }

    /**
     * 获得演员的代表作
     */
    @GetMapping("getMasterpiece")
    public Result getMasterpiece(@RequestParam("performerId") String performerId) {
        PerformerMapper baseMapper = (PerformerMapper) performerService.getBaseMapper();
        List<Movie> masterpiece = baseMapper.getMasterpiece(performerId);
        return new Result(masterpiece != null, masterpiece);
    }

    @GetMapping("getAllMovie")
    public Result getAllMovie(@RequestParam("performerId") String performerId) {
        PerformerMapper baseMapper = (PerformerMapper) performerService.getBaseMapper();
        List<Movie> allMovies = baseMapper.getAllMovies(performerId);
        return new Result(allMovies != null, allMovies);
    }

    /**
     * 保存演员信息
     *
     * @param
     * @return
     */
    @PostMapping("save")
    public Result savePerformer(@RequestParam("name") String name,
                                @RequestParam("englishName") String englishName,
                                @RequestParam("birthday") String birthday,
                                @RequestParam("description") String description,
                                @RequestParam("img") MultipartFile img) {
        Performer performer = new Performer();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = null;
        try {
            date = dateFormat.parse(birthday);
        } catch (ParseException e) {
            System.out.println("日期格式错误！");
            e.printStackTrace();
        }
        performer.setName(name).setEnglishName(englishName).setBirthday(date).setDescription(description);
        boolean savePer = performerService.save(performer);
        boolean saveImg = false;
        try {
            saveImg = performerService.saveImg(img, performer);
        } catch (IOException e) {
            log.error("图片上传失败");
            e.printStackTrace();
        }
        boolean flag = saveImg && savePer;
        return new Result(flag, flag ? "演员数据保存成功" : "演员数据保存失败");

    }

    /**
     * 获取演员列表
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("listPerformers/{currentPage}/{pageSize}")
    public Result listPerformers(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @RequestParam(required = false) String performerKeyword) {
        if (performerKeyword == null) {
            performerKeyword = "";
        }
        IPage<Performer> page = performerService.getAllByPage(currentPage, pageSize, performerKeyword);
        return new Result(page != null, page);
    }

    @GetMapping("getPerformer/{performerId}")
    public Result getPerformer(@PathVariable String performerId) {
        Performer performer = performerService.getById(performerId);
        return new Result(performer != null, performer);
    }

    @PutMapping("editPerformer")
    public Result editPerformer(@RequestParam("name") String name,
                                @RequestParam("performerId") String performerId,
                                @RequestParam("englishName") String englishName,
                                @RequestParam("birthday") Date birthday,
                                @RequestParam("description") String description,
                                @RequestParam(value = "img", required = false) MultipartFile img) {
        Performer performer = new Performer();

        performer.setPerformerId(performerId)
                .setName(name)
                .setEnglishName(englishName)
                .setBirthday(birthday)
                .setDescription(description);
        boolean updatePer = performerService.updateById(performer);
        boolean updateImg = false;
        if (img != null) {
            try {
                updateImg = performerService.saveImg(img, performer);
            } catch (IOException e) {
                log.error("图片上传失败");
                e.printStackTrace();
            }
        } else {
            updateImg = true;
        }

        boolean flag = updateImg && updatePer;
        return new Result(flag, flag ? "信息修改成功" : "信息修改失败");
    }
}

