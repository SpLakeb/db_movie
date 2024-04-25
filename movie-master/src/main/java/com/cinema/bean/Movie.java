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
 * (Movie)��ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Movie extends Model<Movie> {
    /**电影唯一标识符ID*/
    @TableId
    private String movieId;
    /**电影中文名*/
    private String movieName;
    /**电影英文名*/
    private String englishName;
    /**电影所属国家和地区*/
    private String country;
    /**电影简介*/
    @TableField(value = "`describe`")
    private String describe;
    /**电影时长，单位分钟*/
    private Integer duration;
    /**电影上映起始时间*/
    private Date releaseTime;
    /**电影下架时间*/
    private Date endTime;
    /**电影票房*/
    private Long boxOffice;
    /**每日票房统计*/
    private Long dailyBoxOffice;
    /**电影图片存储路径*/
    private String imgUrl;
    /**期待此电影的人数*/
    private Long expect;
    /**电影所有评论分数的总和*/
    private Double score;
    /**当前电影的评论数量*/;
    private Integer commentsNumber;
    /**电影预告片封面路径*/
    private String previewImgUrl;
    /**电影预告片路径*/
    private String previewUrl;
}

