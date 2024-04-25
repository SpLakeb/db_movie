package com.cinema.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.mapper.MovietypeMapper;
import com.cinema.bean.Movietype;
import com.cinema.service.MovietypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Movietype)�����ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Service("movietypeService")
public class MovietypeServiceImpl extends ServiceImpl<MovietypeMapper, Movietype> implements MovietypeService {

    @Autowired
    private MovietypeMapper movietypeMapper;

    @Override
    public Boolean delete(String movieId) {
        LambdaQueryWrapper<Movietype> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Movietype::getMovieId, movieId);
        return movietypeMapper.delete(lqw) > 0;
    }

    @Override
    public Boolean insert(Movietype movietype) {
        return movietypeMapper.insert(movietype) > 0;
    }

    @Override
    public Boolean modify(Movietype movietype) {
        return movietypeMapper.updateById(movietype) > 0;
    }

    @Override
    public Movietype get(String movieId) {
        return movietypeMapper.selectById(movieId);
    }
}

