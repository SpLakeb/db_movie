package com.cinema.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.mapper.OrderMapper;
import com.cinema.bean.Order;
import com.cinema.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Order)�����ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    OrderMapper orderMapper;

    @Override
    public List<Order> getOrders(String userId) {
        return orderMapper.getOrders(userId);
    }

    @Override
    public List<Order> getOrderDetails(String userId, String orderId) {
        return orderMapper.getOrderDetails(userId, orderId);
    }
}

