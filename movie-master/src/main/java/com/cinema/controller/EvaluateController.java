package com.cinema.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cinema.bean.Evaluate;
import com.cinema.bean.Expect;
import com.cinema.bean.User;
import com.cinema.controller.utils.Result;
import com.cinema.service.EvaluateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * (Evaluate)����Ʋ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@RestController
@RequestMapping("/api/evaluate")
@Slf4j
public class EvaluateController {
    @Resource
    private EvaluateService evaluateService;

    /**
     * 根据电影ID查询出相应评论
     *
     * @param movieId
     * @return
     */
    @GetMapping("/movieEvaluate")
    public Result movieEvaluate(String movieId) {
        List<Evaluate> evaluates = evaluateService.selectEvaluateByMovieId(movieId);
        return new Result(evaluates != null, evaluates);
    }
    /**
     * 根据电影ID查询出相应评论
     *
     * @param movieId
     * @return
     */
    @GetMapping("/movieEvaluate/{currentPage}/{pageSize}/{movieId}")
    public Result movieEvaluatePage(@PathVariable int currentPage, @PathVariable int pageSize, @PathVariable String movieId) {
        IPage<Evaluate> evaluates = evaluateService.selectEvaluateByMovieIdPage(currentPage, pageSize, movieId);
        return new Result(evaluates != null, evaluates);
    }
    /**
     * 根据用户ID查询出相应评论
     *
     * @param userId
     * @return
     */
    @GetMapping("/userIdEvaluate/{currentPage}/{pageSize}/{userId}")
    public Result userEvaluate(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @PathVariable String userId) {
        IPage<Evaluate> evaluates = evaluateService.selectEvaluateByUserId(currentPage,pageSize,userId);
        return new Result(evaluates != null, evaluates);
    }

    /***
     * description:
     * @param userId, movieId
     * @return com.cinema.controller.utils.Result
     */
    @GetMapping("/deleteEvaluate/{userId}/{movieId}")
    public Result deleteEvaluate(@PathVariable String userId, @PathVariable String movieId){
        Evaluate evaluate = new Evaluate();
        evaluate.setUserId(userId).setMovieId(movieId);
        return new Result(evaluateService.delete(evaluate));
    }
    /**
     * 对电影进行评价
     */
    @GetMapping("/setEvaluate")
    public Result setEvaluate(String movieId, String content, Double grade, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Evaluate evaluate = new Evaluate(user.getUserId(), movieId, new Date(), content, grade);
        log.info("Evaluate: " + evaluate);
        log.info("grade： " + grade);
        QueryWrapper<Evaluate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getUserId());
        queryWrapper.eq("movie_id", movieId);
        if (evaluateService.getOne(queryWrapper) != null) {
            boolean update = evaluateService.update(evaluate, queryWrapper);
            return new Result(update, update ? "评价成功!" : "评价失败!");
        } else {
            boolean save = evaluateService.save(evaluate);
            return new Result(save, save ? "评价成功!" : "评价失败!");
        }

    }

    /**
     * 查看用户对此电影是否评价过
     *
     * @return
     */
    @GetMapping("/checkUserEvaluate")
    public Result checkUserEvaluate(String movieId, HttpSession session) {
        QueryWrapper<Evaluate> queryWrapper = new QueryWrapper<>();

        User user = (User) session.getAttribute("user");
        queryWrapper.eq("user_id", user.getUserId());
        queryWrapper.eq("movie_id", movieId);
        Evaluate evaluate = evaluateService.getOne(queryWrapper);
        return new Result(evaluate != null, evaluate);
    }
}

