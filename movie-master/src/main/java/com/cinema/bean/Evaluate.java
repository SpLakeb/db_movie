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
 * (Evaluate)��ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Evaluate extends Model<Evaluate> {
    /**用户ID*/
    private String userId;
    /**电影ID*/
    private String movieId;
    /**评价时间*/
    private Date evaluateTime;
    /**评价内容*/
    private String content;
    /**评价分数*/
    private Double grade;
    /**用户名*/
    @TableField(exist = false)
    private String username;
    /**用户头像*/
    @TableField(exist = false)
    private String avatar;

    public Evaluate(String userId, String movieId, Date evaluateTime, String content, Double grade) {
        this.userId = userId;
        this.movieId = movieId;
        this.evaluateTime = evaluateTime;
        this.content = content;
        this.grade = grade;
    }
}

