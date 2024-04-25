package com.cinema;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cinema.bean.Cinema;
import com.cinema.bean.Movie;
import com.cinema.bean.Performer;
import com.cinema.bean.User;
import com.cinema.controller.SearchController;
import com.cinema.controller.utils.Result;
import com.cinema.mapper.PerformerMapper;
import com.cinema.service.*;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.web.client.match.ContentRequestMatchers;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.*;

@SpringBootTest
@AutoConfigureMockMvc
class TianTianCinemaApplicationTests {

//    @Resource
//    StringRedisTemplate stringRedisTemplate;
//
//    @Test
//    void contextLoads(@Autowired MockMvc mvc) throws Exception {
//        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/search?name=影");
//        ResultActions perform = mvc.perform(builder);
//        ContentRequestMatchers content = MockRestRequestMatchers.content();
//        StatusResultMatchers status = MockMvcResultMatchers.status();
//        ResultMatcher ok = status.isOk();
//        perform.andExpect(ok);
//    }
//
//    @Autowired
//    PerformerMapper performerMapper;
//
//    @Test
//    void test() {
//        Long boxOffice = performerMapper.getBoxOffice("10000");
//        System.out.println(boxOffice);
//    }
//
//    @Test
//    void test1() {
//        String[] brands = {
//
//                "金逸影城", "万达影城"
//                , "银兴国际影城"
//                , "CGV影城"
//                , "横店影视"
//                ,
//                "华夏国际影城",
//                "中影国际影城",
//                "金逸星汇影城",
//                "大地影院 ",
//                "烽禾影城",
//                "银兴电影城",
//                "春天国际影城",
//                "恒大嘉凯影城",
//                "德信影城",
//                "橙天嘉禾影城",
//                "木槿校园影城",
//                "华谊兄弟",
//                "中影数字国际影城",
//                "星美国际影城",
//                "中影星美国际影城",
//                "万象影城",
//                "幸福蓝海国际影城",
//                "卢米埃影城",
//                "博纳国际影城",
//                "星轶starx影城", "百老汇影城", "嘉裕国际影城"
//        };
//
//        System.out.println(brands.length);
//    }
//
//
//////    插入数据
////
////    @PostMapping("/add")
////    public void add(@RequestBody Cinema cinema){
////
////        cinema.setBrand(brands[random.nextInt(brands.length)]);
////
////        String cinemaId = UUID.randomUUID().toString()
////                .replaceAll("-", "").substring(0, 19);
////
////        cinema.setCinemaId(cinemaId);
////        cinemaService.save(cinema);
////
////        hallService.add(cinemaId);
////
////    }
//
//    @Test
//    void testGeo() {
//
//        RedisGeoCommands.GeoRadiusCommandArgs args =
//                RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs()
//                        .includeDistance()
//                        .sortAscending()
//                        .includeCoordinates();
//
//        GeoResults<RedisGeoCommands.GeoLocation<String>> radius = stringRedisTemplate.opsForGeo().radius("cinemaList",
//                "d603028443b84f7aa9a9a0e679c0a8db",
//                new Distance(10000000, Metrics.KILOMETERS),
//                args);
//
//        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> geoResults = radius.getContent();
//
//
//        for (int i = 1; i < geoResults.size(); i++) {
//
//            ArrayList<Object> list = new ArrayList<>();
//            list.add("cinemaName");
//            list.add("brand");
//            list.add("address");
//
//            List<Object> multiGet = stringRedisTemplate.opsForHash()
//                    .multiGet("cinemaInfo" + geoResults.get(i).getContent().getName(), list);
//
//            System.out.println(multiGet);
//            System.out.println(geoResults.get(i).getDistance());
//
//        }
//    }
}
