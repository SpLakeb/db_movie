package com.cinema.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.mapper.HallMapper;
import com.cinema.bean.Hall;
import com.cinema.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Hall)�����ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Service("hallService")
public class HallServiceImpl extends ServiceImpl<HallMapper, Hall> implements HallService {

    @Autowired
    HallMapper hallMapper;

    @Override
    public List<Hall> getByCinemaId(String cinemaId) {
        return hallMapper.getByCinemaId(cinemaId);
    }

    @Override
    public List<Hall> getAllByCinemaId(String cinemaId) {
        return hallMapper.getAllByCinemaId(cinemaId);
    }

    @Override
    public List<Hall> getAllByHallId(String hallId) {
        return hallMapper.getByCinemaId(hallId);
    }

    @Override
    public List<Hall> getAllByType(String type) {
        return hallMapper.getAllByType(type);
    }
}

