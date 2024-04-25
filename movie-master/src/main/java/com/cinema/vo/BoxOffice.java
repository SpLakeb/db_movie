package com.cinema.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoxOffice {

    private Date orderDate;
    private String movieName;
    private Double totalPrice;
}
