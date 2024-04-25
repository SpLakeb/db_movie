package com.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cinema.bean.Cinema;
import com.cinema.bean.Movie;
import com.cinema.bean.Performer;
import com.cinema.bean.Scenes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * (Cinema)�����ӿ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:08
 */
public interface CinemaService extends IService<Cinema> {
    /**
     * 获得此影院的所有电影
     *
     * @param cinemaId 影院Id
     * @return 所有电影的集合,按时间增序
     */
    List<Movie> getAllMoviesInCinema(String cinemaId);

    /**
     * 获得此影院此电影的所有排片信息
     *
     * @param movieId  电影ID
     * @param cinemaId 影院ID
     * @return 所有排片信息的List集合
     */
    List<Scenes> getAllScenes(String cinemaId, String movieId);

    /**
     * 获得所有电影的品牌
     * @return 所有电影的品牌
     */
    List<String> getAllBrands();

    /**
     * 获得所有的影院类型
     * @return 所有的影院的集合
     */
    List<String> getAllType();

    /**
     * 将影院list存入
     * @param list
     */
    void pushListToRedis(List<Cinema> list);

    /**
     * 根据cinemaId，获取distance
     * @param cinemaId
     * @param key
     * @return
     */
    Double getDistanceByCinemaId(String cinemaId,String key);

    IPage<Cinema> getAllByPage(Integer currentPage, Integer pageSize);

    /**
     * 通过关键字查询匹配度最高的影院
     * @param keyWord
     * @return
     */
    List<Cinema> getSuggestionsCinema(String keyWord);
}

