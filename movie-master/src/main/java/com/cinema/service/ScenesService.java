package com.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cinema.bean.Scenes;

import java.util.List;

/**
 * 电影场次表(Scenes)�����ӿ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
public interface ScenesService extends IService<Scenes> {
    /**
     * 查询影院的总票房
     * @return
     */
    List<Scenes> getTotalPrice();
    /***
     * description:....
     * * @param movieId
     * @return java.util.List<com.cinema.bean.Scenes>
     */
    List<Scenes> getAllByMovieId(String movieId);

    /**
     * description:.....
     * @param hallId
     * @return java.util.List<com.cinema.bean.Scenes>
     */
    List<Scenes> getAllByHallId(String hallId);


}

