package com.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cinema.bean.Scenes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 电影场次表(Scenes)�����ݿ���ʲ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Mapper
@Repository
public interface ScenesMapper extends BaseMapper<Scenes> {
    /***
     * description:...
     * @param movieId
     * @return java.util.List<com.cinema.bean.Scenes>
     */
    List<Scenes> getAllByMovieId(String movieId);


    /***
     * description:
     * @param cinemaId, hallId
     * @return java.util.List<com.cinema.bean.Scenes>
     */


    /***
     * description:
     * @param hallId
     * @return java.util.List<com.cinema.bean.Scenes>
     */
    List<Scenes> getAllByHallId(String hallId);
    /**
     * 查询影院的总票房
     * @return
     */
    List<Scenes> getTotalPrice();
}

