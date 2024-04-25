package com.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cinema.bean.Movie;
import com.cinema.bean.Type;
import com.cinema.vo.BoxOffice;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * (Movie)�����ӿ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
public interface MovieService extends IService<Movie> {
    //    查询评分前10电影
    List<Movie> topMovie();

    //    查询评分前100电影
    List<Movie> allTopMovie();

    /**
     * 通过通过电影ID查询电影类型
     * @param movieId 电影ID
     * @return 类型ID和类型中文名
     */
    List<Type> movieType(String movieId);

    /**
     * 获取电影的区域列表
     * @return
     */
    List<String> countryList();

    /**
     * 获取年代列表
     * @return
     */
    List<String> yearList();

    /**
     * 保存文件
     * @param img
     * @param previewImg
     * @param preview
     * @param movie
     * @return
     * @throws IOException
     */
    Boolean saveMedia(MultipartFile img, MultipartFile previewImg, MultipartFile preview, Movie movie) throws IOException;

    IPage<Movie> getAllMovie(int currentPage, int pageSize, String keyword, String condition);

    /**
     * 查询所有的电影不需要任何条件
     * @return
     */
    List<Movie> movieList();

    /**
     * 通过关键字查询匹配度最高的
     * @param keyWord
     * @return
     */
    List<Movie> getSuggestionsMovie(String keyWord);

    List<BoxOffice> getDailyBoxOffice();
}

