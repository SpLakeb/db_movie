package com.cinema.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 28794
 * @ClassName Expect.java
 * @since 2022-03-05 11:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Expect {
    private String userId;
    private String movieId;
    /**
     * 0 表示 不期待
     * 1 表示 期待
     */
    private Integer isExpected;

    @TableField(exist = false)
    public static final Integer NOT_EXPECTED = 0;
    @TableField(exist = false)
    public static final Integer EXPECTED = 1;
}
