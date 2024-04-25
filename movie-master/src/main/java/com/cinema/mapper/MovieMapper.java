package com.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cinema.bean.Movie;
import com.cinema.bean.Type;
import com.cinema.vo.BoxOffice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * (Movie)�����ݿ���ʲ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Mapper
public interface MovieMapper extends BaseMapper<Movie> {
    @Select("select * from t_movie order by (score) desc limit 0,10")
    List<Movie> topMovie();

    @Select("select * from t_movie where comments_number !=0 order by (score/comments_number) desc limit 0,100")
    List<Movie> allTopMovie();

    /**
     * 根据movieID查询对应的movie类型
     * @param movieId
     * @return
     */
    @Select("select t.type_id,type_name from t_movietype mt,t_type t where mt.type_id=t.type_id and movie_id=#{movieId}")
    List<Type> movieType(String movieId);


    /**
     * 获取电影的区域列表
     * @return
     */
    @Select("select distinct country from t_movie")
    List<String> countryList();

    /**
     * 获取年代列表
     * @return
     */
    @Select("select distinct year(release_time) as years from t_movie order by years desc")
    List<String> yearList();

    /**
     * 添加文件路径
     * @param movie
     */
    @Update("update t_movie " +
            "set img_url = #{imgUrl}, preview_img_url = #{previewImgUrl}, preview_url=#{previewUrl} " +
            "where movie_id = #{movieId}")
    void updateMediaById(Movie movie);

    @Select("select * from t_boxoffice")
    List<BoxOffice> selectDailyBoxOffice();

    /**
     * 通过关键字查询匹配度最高的
     * @param keyWord
     * @return
     */
    List<Movie> getSuggestionsMovie(String keyWord);
}

