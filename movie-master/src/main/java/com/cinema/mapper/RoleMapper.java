package com.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cinema.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Role)
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select r.*,name,img_url from t_role r,t_performer p where r.performer_id=p.performer_id and movie_id=#{movieId}")
    List<Role> selectMovieRole(String MovieId);
}

