package com.cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Hzk
 */
@SpringBootApplication
@EnableScheduling
public class TianTianCinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TianTianCinemaApplication.class, args);
    }

}
