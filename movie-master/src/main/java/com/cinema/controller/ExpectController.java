package com.cinema.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cinema.bean.Expect;
import com.cinema.bean.User;
import com.cinema.controller.utils.Result;
import com.cinema.service.ExpectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 28794
 * @ClassName ExpectController.java
 * @since 2022-03-05 11:03
 */
@RestController
@RequestMapping("/api/expect")
public class ExpectController {
    @Resource
    ExpectService expectService;

    @GetMapping("/getExpect")
    public Result getExpect(String movieId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new Result(false);
        } else {
            QueryWrapper<Expect> expectQueryWrapper = new QueryWrapper<>();
            expectQueryWrapper.eq("user_id", user.getUserId());
            expectQueryWrapper.eq("movie_id", movieId);
            Expect expect = expectService.getOne(expectQueryWrapper);
            if (expect == null || expect.getIsExpected().equals(Expect.NOT_EXPECTED)) {
                return new Result(false);
            } else {
                return new Result(true);
            }
        }
    }

    @GetMapping("/setExpect")
    public Result setExpect(boolean isExpected, String movieId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new Result(false, "请先登录");
        } else {
            Expect expect = new Expect(user.getUserId(), movieId, isExpected ? Expect.EXPECTED : Expect.NOT_EXPECTED);
            QueryWrapper<Expect> expectQueryWrapper = new QueryWrapper<>();
            expectQueryWrapper.eq("user_id", user.getUserId());
            expectQueryWrapper.eq("movie_id", movieId);
            Expect one = expectService.getOne(expectQueryWrapper);
            if (one != null) {
                expectQueryWrapper.clear();
                expectQueryWrapper.eq("user_id", user.getUserId());
                expectQueryWrapper.eq("movie_id", movieId);
                boolean update = expectService.update(expect, expectQueryWrapper);
                return new Result(update);
            }else{
                boolean save = expectService.save(expect);
                return new Result(save);
            }
        }
    }
}
