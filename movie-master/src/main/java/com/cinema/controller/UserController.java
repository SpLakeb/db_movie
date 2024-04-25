package com.cinema.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cinema.bean.Performer;
import com.cinema.bean.Performer;
import com.cinema.bean.User;
import com.cinema.controller.utils.RedisUtil;
import com.cinema.controller.utils.Result;
import com.cinema.controller.utils.SixCodeUtils;
import com.cinema.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * (User)����Ʋ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用户注册
     */
    @PostMapping("/add")
    public Result addUser(@RequestBody User user) {
        boolean save = userService.save(user);
        return new Result(save);
    }

    /**
     * 自动注册or登录
     *
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/autoAddOrLogin")
    public Result autoAddUser(@RequestBody User user, HttpSession session) {

        //判断user是否存在
        User one = userService.getOne(
                new QueryWrapper<User>().eq("email", user.getEmail()));

        if (one != null) {
            //如果存在，存入session
            session.setAttribute("user", one);
            return new Result(true);
        } else {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            user.setUsername("天天用户" + uuid);
            user.setPassword(uuid);

            boolean save = userService.save(user);

            if (save) {
                User userAutoAdd = userService.getOne(
                        new QueryWrapper<User>().eq("email", user.getEmail()));

                session.setAttribute("user", userAutoAdd);

                new Thread(() -> {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setSubject("来自天天影院官方的提示");

                    String sixCode = SixCodeUtils.getSixCode();

                    message.setText("天天影院官方提醒您，您刚刚自动注册了天天影院账号，密码为:"
                            + uuid + ",请快速前往个人中心修改密码以及个人信息");

                    message.setFrom("2488689182@qq.com");
                    message.setTo(user.getEmail());

                    mailSender.send(message);
                }).start();

            }

            return new Result(save);
        }

    }

    /**
     * 验证邮箱是否可以注册
     */
    @GetMapping("/checkEmail")
    public Result checkEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        User one = userService.getOne(wrapper);

        return new Result(one == null);
    }

    /**
     * 用户登录验证
     */
    @PostMapping("/login")
    public Result checkUser(@RequestBody User user, HttpSession session) {
        String email = user.getEmail();
        String password = user.getPassword();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        wrapper.eq("password", password);
        User user1 = userService.getOne(wrapper);
        System.out.println(session.getAttribute("user"));
        session.setAttribute("user", user1);
        System.out.println(session.getAttribute("user"));
        return new Result(user1 != null);
    }

    @GetMapping("/test")
    public Result test(){
        return new Result(true, "123");
    }
    /**
     * 执行发送邮件
     *
     * @param email
     * @return
     */
    @GetMapping("/sendEmailToCheck")
    public Result sendEmailToCheck(String email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("来自天天影院官方的验证码");

            String sixCode = SixCodeUtils.getSixCode();

            message.setText("天天影院官方提醒您，您正在操作天天影院账号，验证码为:"
                    + sixCode + "，有效时间为60s，请保管好您的验证码，不要泄露给他人。");

            message.setFrom("2488689182@qq.com");
            message.setTo(email);

            mailSender.send(message);

            //将验证码存入对应邮箱为key的redis中
            redisUtil.setEx(email, sixCode, 60, TimeUnit.SECONDS);

            return new Result(true);
        } catch (MailException e) {
            e.printStackTrace();
            return new Result(false);
        }
    }

    /**
     * 判断验证码是否正确
     *
     * @param email
     * @param sixCode
     * @return
     */
    @GetMapping("/checkSixCode")
    public Result checkSixCode(String email, String sixCode) {

        String successCode = redisUtil.get(email);

        if (successCode == null) {
            return new Result(false);
        }

        if (sixCode.equals(successCode)) {
            return new Result(true);
        }

        return new Result(false);
    }

    /**
     * 判断验证码是否失效
     *
     * @param email
     * @return
     */
    @GetMapping("/hasOverTime")
    public Result hasOverTime(String email) {

        //判断key是否存在
        if (redisUtil.hasKey(email)) {
            return new Result(false, redisUtil.getExpire(email, TimeUnit.SECONDS));
        }

        return new Result(true);

    }

    /**
     * 获取个人信息
     *
     * @param session
     * @return
     */
    @GetMapping("/myInfo")
    public Result myInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println(user!=null);
        return new Result(user != null, user);
    }


    @GetMapping("listUsers/{currentPage}/{pageSize}")
    public Result listPerformers(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        IPage<User> page = userService.getAllByPage(currentPage, pageSize);
        return new Result(page != null, page);
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session) {

        session.removeAttribute("user");
        return new Result(true);
    }

    /**
     * 修改用户信息
     *
     * @param username
     * @param gender
     * @param birth
     * @param img
     * @param signature
     * @param img
     * @return
     */
    @PostMapping("/editUserInfo")
    public Result editUserInfo(String username,
                               String gender,
                               Date birth,
                               String signature,
                               @RequestParam(value = "img", required = false) MultipartFile img, HttpSession session) {
        User user = (User) session.getAttribute("user");
        System.out.println("date : " + birth);
        user.setUsername(username)
                .setGender(gender)
                .setSignature(signature)
                .setBirth(birth);
        boolean save = userService.updateById(user);
        boolean saveImg = false;

        if (img != null) {
            try {
                saveImg = userService.saveImg(img, user);
            } catch (IOException e) {
                log.error("图片上传失败!");
                e.printStackTrace();
            }
        }else{
            saveImg = true;
        }


        boolean flag = save && saveImg;
        return new Result(flag, flag?"信息修改成功":"信息修改失败");
    }

    /**
     * 更新用户的定位，并用uuid作为key
     * @param x
     * @param y
     * @param session
     */
    @GetMapping("/updateWhere")
    public void updateWhere(double x,double y,HttpSession session){

        System.out.println();

        Object uuid = session.getAttribute("key");

        if (uuid == null){
            uuid = UUID.randomUUID().toString().replaceAll("-","");
        }

        String uuid1 = (String) uuid;
        stringRedisTemplate.opsForGeo().add("cinemaList",
                new Point(x,y),uuid1);

        session.setAttribute("key",uuid1);
    }

    /**
     * 检查邮箱是否被使用
     * @param email
     * @return
     */
    @GetMapping("/checkEmailUsed")
    public Result checkEmailUsed(String email){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);

        User one = userService.getOne(wrapper);

        return new Result(one!=null,one);
    }

    @GetMapping("/updatePassword")
    public Result updatePassword(String email,String password){
        System.out.println(email+password);
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("email",email);
        wrapper.set("password",password);
        userService.update(wrapper);
        return new Result(true);
    }
}

