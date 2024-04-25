package com.cinema.bean;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * (Ticket)��ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Accessors(chain = true)
@SuppressWarnings("serial")
public class Ticket extends Model<Ticket> {

    private String scenesId;

    private String orderId;
    /**座位号*/
    private Integer seatNo;
}

