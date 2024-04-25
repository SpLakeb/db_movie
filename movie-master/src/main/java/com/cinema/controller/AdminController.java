package com.cinema.controller;

import com.cinema.bean.Admin;
import com.cinema.controller.utils.Result;
import com.cinema.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author 28794
 * @ClassName AdminController.java
 * @since 2022-02-28 18:43
 */
@RestController
@RequestMapping("/api/admin")
@Slf4j
public class AdminController {

    @Resource
    AdminService adminService;

    @PostMapping("/adminLogin")
    public Result adminLogin(@RequestBody Admin admin, HttpSession session) {
        System.out.println(admin);
        Admin adminServiceById = adminService.getById(admin.getUsername());
        if (adminServiceById == null) {
            return new Result(false, "不存在此用户!");
        } else if (!adminServiceById.getPassword().equals(admin.getPassword())) {
            return new Result(false, "密码输入错误!");
        } else {
            session.setAttribute("admin", admin);
            return new Result(true, "登录成功!OvO");
        }
    }

    @GetMapping("/checkAdminStatus")
    public Result checkAdminStatus(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            return new Result(true);
        }else {
            return new Result(false);
        }
    }

}
