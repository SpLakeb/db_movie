package com.cinema.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.bean.User;
import com.cinema.mapper.EvaluateMapper;
import com.cinema.bean.Evaluate;
import com.cinema.service.EvaluateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Evaluate)�����ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Service("evaluateService")
public class EvaluateServiceImpl extends ServiceImpl<EvaluateMapper, Evaluate> implements EvaluateService {
    @Resource
    private EvaluateMapper evaluateMapper;

    @Override
    public List<Evaluate> selectEvaluateByMovieId(String movieId) {
        return evaluateMapper.selectEvaluateByMovieId(movieId);
    }

    @Override
    public IPage<Evaluate> selectEvaluateByMovieIdPage(int currentPage, int pageSizeString, String movieId) {
        IPage<Evaluate> page = new Page<>(currentPage, pageSizeString);
        List<Evaluate> evaluateList = evaluateMapper.selectEvaluateByMovieId(movieId);
        int start = evaluateList.size() / pageSizeString * (currentPage - 1);
        page.setRecords(evaluateList.subList(start, Math.min(start + pageSizeString, evaluateList.size())));
        return page;
    }

    @Override
    public Boolean delete(Evaluate evaluate) {
        return evaluateMapper.delete(evaluate) > 0;
    }

    @Override
    public IPage<Evaluate> selectEvaluateByUserId(int currentPage, int pageSizeString, String userId) {
        IPage<Evaluate> page = new Page<>(currentPage, pageSizeString);
        LambdaQueryWrapper<Evaluate> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Evaluate::getUserId, userId);
        return evaluateMapper.selectPage(page, lqw);
    }
}

