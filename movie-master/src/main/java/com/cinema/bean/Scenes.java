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
 * 电影场次表(Scenes)��ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Scenes extends Model<Scenes> {
    /**场次ID*/
    @TableId
    private String scenesId;
    /**放映厅ID*/
    private String hallId;
    /**电影ID*/
    private String movieId;
    /**该场次开始放映时间*/
    private Date dateStart;
    /**该场次结束放映时间*/
    private Date dateEnd;
    /**价格*/
    private Double price;

    @TableField(exist = false)
    private String hallName;

    @TableField(exist = false)
    private String cinemaId;

    @TableField(exist = false)
    private String totalPrice;

    @TableField(exist = false)
    private String cinemaName;
}

