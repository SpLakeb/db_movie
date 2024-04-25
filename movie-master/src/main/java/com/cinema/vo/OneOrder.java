package com.cinema.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author hzk
 * @date 2022/3/9
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OneOrder {

    private String orderId;

    private String scenesId;

    private List<Integer> seatsNo;
}
