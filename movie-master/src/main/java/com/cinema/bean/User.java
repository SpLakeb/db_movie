package com.cinema.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * (User)��ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:10
 */
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class User extends Model<User> {
    /**用户id，唯一标识*/
    @TableId
    private String userId;
    /**用户邮箱，用于登录*/
    private String email;
    /**用户密码，用于登录*/
    private String password;
    /**用户名,可重复*/
    private String username;
    /**用户性别，默认为保密*/
    private String gender;
    /**出生日期*/
    private Date birth;
    /**头像路径*/
    private String avatar;
    /**个性签名*/
    private String signature;
}

