package com.cinema.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.bean.Movie;
import com.cinema.bean.Performer;
import com.cinema.bean.Scenes;
import com.cinema.mapper.CinemaMapper;
import com.cinema.bean.Cinema;
import com.cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Cinema)�����ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:08
 */
@Service("cinemaService")
public class CinemaServiceImpl extends ServiceImpl<CinemaMapper, Cinema> implements CinemaService {

    @Resource
    CinemaMapper cinemaMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Override
    public List<Movie> getAllMoviesInCinema(String cinemaId) {
        return cinemaMapper.getAllMoviesInCinema(cinemaId);
    }

    @Override
    public List<Scenes> getAllScenes(String cinemaId, String movieId) {
        return cinemaMapper.getAllScenes(cinemaId, movieId);
    }

    @Override
    public List<String> getAllBrands() {
        return cinemaMapper.getAllCinemaBrand();
    }

    @Override
    public List<String> getAllType() {
        return cinemaMapper.getAllType();
    }

    /**
     * 将影院list存入redis
     * @param list
     */
    @Override
    public void pushListToRedis(List<Cinema> list) {

        /**
         * 将id作为key，经纬存入geo，其余信息存入hash
         */
        for (Cinema cinema : list) {
            stringRedisTemplate.opsForGeo().add("cinemaList",
                    new Point((cinema.getAddressX()),cinema.getAddressY()),
                    cinema.getCinemaId());

            Map<String, String> map = new HashMap<>(16);

            map.put("cinemaName", cinema.getCinemaName());
            map.put("brand", cinema.getBrand());
            map.put("address", cinema.getAddress());

            stringRedisTemplate.opsForHash().putAll("cinemaInfo"+cinema.getCinemaId(),map);

        }

    }

    /**
     * 根据影院Id获取distance（距离）
     * @param cinemaId
     * @param key
     * @return
     */
    @Override
    public Double getDistanceByCinemaId(String cinemaId, String key) {

        Distance distance = stringRedisTemplate.opsForGeo().distance("cinemaList", key, cinemaId,
                Metrics.KILOMETERS);

        return distance.getValue();
    }

    /***
     * description:获取所有影院
     * @param currentPage, pageSize
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.cinema.bean.Cinema>
     */
    @Override
    public IPage<Cinema> getAllByPage(Integer currentPage, Integer pageSize) {
        IPage<Cinema> iPage = new Page<>(currentPage, pageSize);
        cinemaMapper.selectPage(iPage, null);
        return iPage;
    }

    @Override
    public List<Cinema> getSuggestionsCinema(String keyWord) {
        return cinemaMapper.getSuggestionsCinema(keyWord);
    }
}

