package com.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cinema.bean.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Order)�����ݿ���ʲ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

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

