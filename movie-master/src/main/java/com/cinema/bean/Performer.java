package com.cinema.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.UUID;

/**
 * (Performer)��ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Performer extends Model<Performer> {
    @TableId(type = IdType.ASSIGN_ID)
    private String performerId;
    /**演员姓名*/
    private String name;
    /**
     * 演员英文名
     */
    private String englishName;
    /**
     * 演员图片地址
     */
    private String imgUrl;
    /**简介*/
    private String description;
    /**出生日期*/
    private Date birthday;

    /**
     * 演员的角色名
     */
    @TableField(exist = false)
    private String roleName;
}

