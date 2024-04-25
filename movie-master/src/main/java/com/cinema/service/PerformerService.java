package com.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cinema.bean.Movie;
import com.cinema.bean.Performer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * (Performer)�����ӿ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
public interface PerformerService extends IService<Performer> {

    /**
     * 查询演员的总票房
     *
     * @param performerId
     * @return
     */
    Long getBoxOffice(String performerId);

    /**
     * 查询演员票房前三的作品
     *
     * @param performerId
     * @return
     */
    List<Movie> getMasterpiece(String performerId);

    List<Movie> getAllMovies(String performerId);

    Boolean saveImg(MultipartFile img, Performer performer) throws IOException;


    /**
     * 分页查询所有演员
     * @param currentPage
     * @param pageSize
     * @return
     */
    IPage<Performer> getAllByPage(int currentPage, int pageSize, String keyword);

    /**
     * 通过关键字查询匹配度最高的演员
     * @param keyWord
     * @return
     */
    List<Performer> getSuggestionsPerformer(String keyWord);
}

