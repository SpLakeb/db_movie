package com.cinema.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.mapper.TicketMapper;
import com.cinema.bean.Ticket;
import com.cinema.service.TicketService;
import org.springframework.stereotype.Service;

/**
 * (Ticket)�����ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Service("ticketService")
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {

}

