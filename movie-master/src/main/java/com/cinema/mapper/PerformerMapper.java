package com.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cinema.bean.Cinema;
import com.cinema.bean.Movie;
import com.cinema.bean.Performer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Performer)�����ݿ���ʲ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Mapper
public interface PerformerMapper extends BaseMapper<Performer> {

    /**
     * 查询演员的总票房
     * @return
     */
    @Select(
            "select sum(m.box_office) from t_movie m,t_role r,t_performer p " +
                    "where m.movie_id = r.movie_id and r.performer_id = p.performer_id " +
                    "and p.performer_id = #{performerId}"
    )
    Long getBoxOffice(String performerId);

    /**
     * 求演员票房前三的作品
     * @param performerId
     * @return
     */
    List<Movie> getMasterpiece(String performerId);

    /**
     * 查询此演员的所有作品
     * @param performerId
     * @return
     */
    List<Movie> getAllMovies(String performerId);


    /**
     * 通过关键字查询匹配度最高的演员
     * @param keyWord
     * @return
     */
    List<Performer> getSuggestionsPerformer(String keyWord);
}

