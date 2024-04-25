package com.cinema.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cinema.bean.*;
import com.cinema.controller.utils.RedisUtil;
import com.cinema.controller.utils.Result;
import com.cinema.service.OrderService;
import com.cinema.service.TicketService;
import com.cinema.vo.OneOrder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * (Order)����Ʋ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @Resource
    private TicketService ticketService;

    @Resource
    RedisUtil redisUtil;

    @Resource
    ScenesController scenesController;

    //    创建订单
    @PostMapping("/addOrder")
    public boolean addOrder(HttpSession session, @RequestBody OneOrder oneOrder) {

        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.eq("scenes_id", oneOrder.getScenesId());

        for (Integer integer : oneOrder.getSeatsNo()) {

            wrapper.eq("seat_no", integer);

            if (ticketService.getOne(wrapper) != null) {
                return false;
            }
        }


//        创建该订单的电影票

        for (Integer seatNo : oneOrder.getSeatsNo()) {
            ticketService.save(new Ticket()
                    .setScenesId(oneOrder.getScenesId())
                    .setOrderId(oneOrder.getOrderId())
                    .setSeatNo(seatNo));
        }

        User user = (User) session.getAttribute("user");
//        创建订单
        return orderService.save(new Order()
                .setOrderId(oneOrder.getOrderId())
                .setUserId(user.getUserId())
                .setOrderDate(new Date()));
    }

    @GetMapping("/getOrders")
    public Result getOrders(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> orderDetails = orderService.getOrders(user.getUserId());
        return new Result(!orderDetails.isEmpty(), orderDetails);
    }

    @GetMapping("/getOrderDetails")
    public Result getOrderDetails(String orderId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> orderDetails = orderService.getOrderDetails(user.getUserId(), orderId);
        return new Result(!orderDetails.isEmpty(), orderDetails);
    }

    /**
     * 将订单临时存入redis，设置10分钟失效
     *
     * @param oneOrder
     * @param session
     */
    @PostMapping("/pushOrderToRedis")
    public void pushOrderToRedis(@RequestBody OneOrder oneOrder, HttpSession session) {

        User user = (User) session.getAttribute("user");

        //存入session中的userId对应的scenesId到redis中，方便我的订单页面查询
        redisUtil.setEx(user.getUserId(), oneOrder.getScenesId(), 10, TimeUnit.MINUTES);

        for (Integer integer : oneOrder.getSeatsNo()) {
            redisUtil.lRightPush(user.getUserId() + ":" + oneOrder.getScenesId(), String.valueOf(integer));
        }

        System.out.println(user.getUserId() + ":" + oneOrder.getScenesId());
        //设置10分钟过期
        redisUtil.expire(user.getUserId() + ":" + oneOrder.getScenesId(), 10, TimeUnit.MINUTES);
    }


    /**
     * 判断是否有订单
     *
     * @param scenesId
     * @param session
     * @return
     */
    @GetMapping("/hasOrderInRedis")
    public Result hasOrderInRedis(String scenesId, HttpSession session) {

        User user = (User) session.getAttribute("user");

        return new Result(redisUtil.hasKey(user.getUserId()));
    }

    @GetMapping("/orderNoHandle")
    public Result getOrdersNoHandle(HttpSession session) {

        User user = (User) session.getAttribute("user");

        String scenesId = redisUtil.get(user.getUserId());

        if (scenesId == null) {
            return new Result(false);
        }

        return scenesController.getScenesInfo(scenesId);

    }

    @GetMapping("/ttl")
    public Result getTtl(HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (!redisUtil.hasKey(user.getUserId())) {
            return new Result(false);
        }

        return new Result(true, redisUtil.getExpire(user.getUserId(), TimeUnit.SECONDS));
    }

    @GetMapping("/cancel")
    public void cancelOrder(HttpSession session) {

        User user = (User) session.getAttribute("user");

        String scenesId = redisUtil.get(user.getUserId());

        redisUtil.delete(Arrays.asList(user.getUserId(), user.getUserId() + ":" + scenesId));
    }

    @GetMapping("/seatsNo")
    public Result seatsNo(HttpSession session) {

        User user = (User) session.getAttribute("user");

        String scenesId = redisUtil.get(user.getUserId());

        List<String> list = redisUtil.lRange(user.getUserId() + ":" + scenesId, 0L, -1L);

        List<Integer> arrayList = new ArrayList<>();

        for (String s : list) {
            arrayList.add(Integer.parseInt(s));
        }

        return new Result(true, arrayList);
    }

    @GetMapping("/deleteOrder")
    public Result deleteOrder(String orderId) {
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_id", orderId);
        boolean remove = orderService.remove(orderQueryWrapper);
        return new Result(remove);
    }
}

