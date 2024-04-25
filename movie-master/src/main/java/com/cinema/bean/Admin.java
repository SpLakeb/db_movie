package com.cinema.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author 28794
 * @ClassName Admin.java
 * @since 2022-02-28 18:40
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")
@Accessors(chain = true)
public class Admin {
    @TableId
    private String username;
    private String password;
}
