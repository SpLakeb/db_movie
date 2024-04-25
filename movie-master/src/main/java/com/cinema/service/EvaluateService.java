package com.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cinema.bean.Evaluate;

import java.util.List;

/**
 * (Evaluate)�����ӿ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
public interface EvaluateService extends IService<Evaluate> {
    /**
     * 根据电影ID查询该电影的评论
     * @param movieId
     * @return
     */
    List<Evaluate> selectEvaluateByMovieId(String movieId);

    IPage<Evaluate> selectEvaluateByMovieIdPage(int currentPage, int pageSizeString, String movieId);


    Boolean delete(Evaluate evaluate);



    IPage<Evaluate> selectEvaluateByUserId(int currentPage, int pageSize, String userId);



}

