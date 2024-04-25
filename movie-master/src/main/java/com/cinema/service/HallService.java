package com.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cinema.bean.Hall;

import java.util.List;

/**
 * (Hall)�����ӿ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
public interface HallService extends IService<Hall> {


    /**
     * description:通过cinemaId查找
     * @param cinemaId  影院ID
     * @return java.util.List<com.cinema.bean.Hall>
     */

    List<Hall> getByCinemaId(String cinemaId);



    /**
     * description:cinemaId
     * @param cinemaId  影院id
     * @return java.util.List<com.cinema.bean.Hall>
     */

    List<Hall> getAllByCinemaId(String cinemaId);

    /**
     * description:cinemaId
     * @param hallId 影厅id
     * @return java.util.List<com.cinema.bean.Hall>
     */

    List<Hall> getAllByHallId(String hallId);

    /**
     * description:type
     * @param type 类型
     * @return java.util.List<com.cinema.bean.Hall>
     */

    List<Hall> getAllByType(String type);

}

