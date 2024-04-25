package com.cinema.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * (Order)��ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Order extends Model<Order> {
    @TableId
    private String orderId;

    private Date orderDate;

    private String userId;

    @TableField(exist = false)
    private String movieName;

    @TableField(exist = false)
    private String movieId;

    @TableField(exist = false)
    private String imgUrl;

    @TableField(exist = false)
    private String cinemaName;

    @TableField(exist = false)
    private String hallName;

    @TableField(exist = false)
    private String dateStart;

    @TableField(exist = false)
    private String price;

    @TableField(exist = false)
    private String seatNo;
}

