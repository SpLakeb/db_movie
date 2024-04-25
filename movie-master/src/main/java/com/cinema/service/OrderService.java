package com.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cinema.bean.Order;

import java.util.List;

/**
 * (Order)�����ӿ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
public interface OrderService extends IService<Order> {

    /**
     * 查询此用户的部分订单信息
     * @param userId 用户ID
     * @return
     */
    List<Order> getOrders(String userId);

    /**
     * 查询此用户的所有订单信息
     * @param userId 用户Id
     * @param orderId 订单Id
     * @return
     */
    List<Order> getOrderDetails(String userId, String orderId);
}

