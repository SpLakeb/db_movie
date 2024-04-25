package com.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cinema.bean.Cinema;
import com.cinema.bean.Movie;
import com.cinema.bean.Scenes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Cinema)�����ݿ���ʲ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:08
 */
@Mapper
public interface CinemaMapper extends BaseMapper<Cinema> {

    /**
     * 获得此影院的所有电影
     *
     * @param cinemaId 影院Id
     * @return 所有电影的集合
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
     * 获得所有的影院
     * @return 所有的影院的集合
     */
    List<String> getAllCinemaBrand();

    /**
     * 获得所有的影院类型
     * @return 所有的影院的集合
     */
    List<String> getAllType();

    /**
     * 通过关键字查询匹配度最高的影院
     * @param keyWord
     * @return
     */
    List<Cinema> getSuggestionsCinema(String keyWord);
}

