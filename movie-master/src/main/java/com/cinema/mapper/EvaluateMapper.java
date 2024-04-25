package com.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cinema.bean.Evaluate;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * (Evaluate)�����ݿ���ʲ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Mapper
public interface EvaluateMapper extends BaseMapper<Evaluate> {
    /***
     * description:....
     * @param movieId
     * @return java.util.List<com.cinema.bean.Evaluate>
     */

    @Select("select e.*,username,avatar from t_evaluate e,t_user u where e.user_id=u.user_id and movie_id=#{movieId}")
    List<Evaluate> selectEvaluateByMovieId(String movieId);

    /**
     * description:...
     * @param userId
     * @return java.util.List<com.cinema.bean.Evaluate>
     */
    @Select("select e.* from t_evaluate e,t_user u where e.user_id=#{userId} and u.user_id=e.user_id" )
    List<Evaluate> selectEvaluateByUserId(String userId);

    /**
     * description:...
     * @param
     * @return java.lang.Boolean
     */
    @Delete("delete from t_evaluate where user_id=#{userId} and movie_id=#{movieId}")
    Integer delete(Evaluate evaluate);
}

