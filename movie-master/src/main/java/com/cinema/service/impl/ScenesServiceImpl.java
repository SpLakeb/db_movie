package com.cinema.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.mapper.ScenesMapper;
import com.cinema.bean.Scenes;
import com.cinema.service.ScenesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import java.util.List;

/**
 * 电影场次表(Scenes)�����ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Service("scenesService")
public class ScenesServiceImpl extends ServiceImpl<ScenesMapper, Scenes> implements ScenesService {

    @Resource
    ScenesMapper scenesMapper;

    /**
     * 查询影院的总票房
     * @return
     */
    @Override
    public List<Scenes> getTotalPrice() {
        return scenesMapper.getTotalPrice();
    }

    @Override
    public List<Scenes> getAllByMovieId(String movieId) {
        return scenesMapper.getAllByMovieId(movieId);
    }

    @Override
    public List<Scenes> getAllByHallId(String hallId) {
        return scenesMapper.getAllByHallId(hallId);
    }

}

