package com.cinema.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cinema.bean.Ticket;
import com.cinema.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Ticket)����Ʋ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    @Resource
    private TicketService ticketService;

    //    查询该场次已被购买的座位号
    @GetMapping("/seatsHasSale")
    public List<Integer> seatsHasSale(String scenesId){

        QueryWrapper<Ticket> wrapper = new QueryWrapper<>();
        wrapper.select("seat_no");
        wrapper.eq("scenes_id",scenesId);
        List<Ticket> tickets = ticketService.list(wrapper);

        List<Integer> list = new ArrayList<>();
        for (Ticket ticket : tickets) {
            list.add(ticket.getSeatNo());
        }
        return list;
    }


}

