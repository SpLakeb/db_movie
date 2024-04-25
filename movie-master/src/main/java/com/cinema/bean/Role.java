package com.cinema.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.beans.Transient;

/**
 * (Role)��ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Role extends Model<Role> {

    private String movieId;

    private String performerId;
    /**饰演的角色*/
    private String roleName;
    /**演员姓名*/
    @TableField(exist = false)
    private String name;
    /**电影名*/
    @TableField(exist = false)
    private String movieName;
    /**演员图片*/
    @TableField(exist = false)
    private String imgUrl;

}

