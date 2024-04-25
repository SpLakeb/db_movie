package com.cinema.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (Cinema)��ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Cinema extends Model<Cinema> {
    /**电影院的ID*/
    @TableId
    private String cinemaId;
    /**电影院的名称*/
        private String cinemaName;
    /**电影院的品牌*/
    private String brand;
    /**电影院地址*/
    private String address;
    /**电影院地址的经度*/
    private Double addressX;
    /**电影院地址的纬度*/
        private Double addressY;

    /**
     * 电影院离当前用户的距离
     */
    @TableField(exist = false)
    private Double distance;
}

