package com.cinema.bean;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (Hall)��ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Hall extends Model<Hall> {
    /**影厅ID，唯一标识*/
    @TableId
    private String hallId;
    /**放映厅名称*/
    private String hallName;
    /**影院ID，标记所属影院*/
    private String cinemaId;
    /**此影厅中座位总数*/
    private Integer seats;
    /**影厅类型*/
    private String type;
}

