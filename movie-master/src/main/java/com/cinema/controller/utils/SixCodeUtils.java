package com.cinema.controller.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author hzk
 * @date 2022/3/2
 **/

public class SixCodeUtils {

    public static String getSixCode(){

        Random random = new Random();

        return ""+random.nextInt(10)
                +random.nextInt(10)
                +random.nextInt(10)
                +random.nextInt(10)
                +random.nextInt(10)
                +random.nextInt(10);
    }
}
