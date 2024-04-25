package com.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cinema.bean.Hall;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * (Hall)�����ݿ���ʲ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Mapper
@Repository
public interface HallMapper extends BaseMapper<Hall> {

     List<Hall> getByCinemaId(String cinemaId);


     /**
      * description:cinemaId....
      * @param cinemaId 电影ID
      * @return java.util.List<com.cinema.bean.Hall>
      */


     List<Hall> getAllByCinemaId(String cinemaId);
     /**
      * description:hallId
      * @param hallId 影厅ID
      * @return java.util.List<com.cinema.bean.Hall>
      */

     List<Hall> getAllByHallId(String hallId);

     /**
      * description:TYPE
      * @param type 影厅类型
      * @return java.util.List<com.cinema.bean.Hall>
      */


     List<Hall> getAllByType(String type);

     /**
      * description:hallName
      * @param hallName 影厅名
      * @return java.util.List<com.cinema.bean.Hall>
      */

     List<Hall> getByHallName(String hallName);



}

