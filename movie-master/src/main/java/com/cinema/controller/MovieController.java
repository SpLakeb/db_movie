package com.cinema.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cinema.bean.Movie;
import com.cinema.bean.Type;
import com.cinema.controller.utils.Constant;
import com.cinema.controller.utils.Result;
import com.cinema.service.MovieService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * (Movie)����Ʋ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Resource
    private MovieService movieService;

    //    全部热映电影，返回电影表
    @GetMapping("/allHotShowing")
    public Result allHotShowing() {

        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.le("release_time", new Date());
        wrapper.ge("end_time", new Date());
        List<Movie> movieList = movieService.list(wrapper);

        return new Result(movieList != null, movieList);
    }

    //    8部票房最高的热映电影，返回长度为8的电影表
    @GetMapping("/hotShowing")
    public Result hotShowing() {

        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.le("release_time", new Date());
        wrapper.ge("end_time", new Date());
        wrapper.orderByAsc("box_office");
        wrapper.last("limit 0,8");
        List<Movie> movieList = movieService.list(wrapper);

        return new Result(movieList != null, movieList);
    }

    //    查询共有多少部热映电影,返回电影数量int
    @GetMapping("/showingNumber")
    public Result showingNumber() {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.le("release_time", new Date());
        wrapper.ge("end_time", new Date());
        int count = movieService.count(wrapper);
        return new Result(count != 0, count);
    }

    //    查询10部最受期待电影，返回长度为10的电影表
    @GetMapping("/expected")
    public Result expected() {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.ge("release_time", new Date());
        wrapper.orderByDesc("expect");
        wrapper.last("limit 0,10");
        List<Movie> movieList = movieService.list(wrapper);

        return new Result(movieList != null, movieList);
    }

    @GetMapping("/oldMovies")
    public Result oldMovies() {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.lt("end_time", new Date());

        List<Movie> movieList = movieService.list(wrapper);

        return new Result(movieList != null, movieList);
    }

    //    查询所有最受期待电影（最多50部），返回长度为50的电影表
    @GetMapping("/allExpected")
    public Result allExpected() {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.ge("release_time", new Date());
        wrapper.orderByDesc("expect");
        wrapper.last("limit 0,50");
        List<Movie> movieList = movieService.list(wrapper);

        return new Result(movieList != null, movieList);
    }

    //    查询评分最高的10部电影，返回长度为10的电影链表
    @GetMapping("/topMovie")
    public Result topMovie() {
        List<Movie> movieList = movieService.topMovie();

        return new Result(movieList != null, movieList);
    }

    //    查询评分最高的100部电影，返回长度为100的电影链表
    @GetMapping("/allTopMovie")
    public Result allTopMovie() {
        List<Movie> movieList = movieService.allTopMovie();

        return new Result(movieList != null, movieList);
    }

    //    查询8个即将上映的电影
    @GetMapping("/comingSoon")
    public Result comingSoon() {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.gt("release_time", new Date());
        wrapper.last("limit 0,8");
        List<Movie> movieList = movieService.list(wrapper);

        return new Result(movieList != null, movieList);
    }

    //    查询全部即将上映的电影
    @GetMapping("/allComingSoon")
    public Result allComingSoon() {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();

        wrapper.gt("release_time", new Date());
        List<Movie> movieList = movieService.list(wrapper);

        return new Result(movieList != null, movieList);
    }

    //    查询即将上映的电影数量
    @GetMapping("/comingNumber")
    public Result comingNumber() {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.gt("release_time", new Date());

        int count = movieService.count(wrapper);
        return new Result(count != 0, count);
    }

    /**
     * 查看今日票房，返回前5名今日票房最高的电影
     */
    @GetMapping("/dailyBoxOffice")
    public Result dailyBoxOffice() {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("daily_box_office");
        wrapper.last("limit 0,5");
        List<Movie> movieList = movieService.list(wrapper);
        return new Result(movieList != null, movieList);
    }

    /**
     * 根据电影ID查询电影相关的详细信息
     */
    @GetMapping("/movieDetails")
    public Result movieDetails(String movieId) {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.eq("movie_id", movieId);
        Movie movie = movieService.getOne(wrapper);
        return new Result(movie != null, movie);
    }

    /**
     * 根据电影ID查询电影类型
     */
    @GetMapping("/movieType")
    public Result movieType(String movieId) {
        List<Type> types = movieService.movieType(movieId);
        return new Result(types != null, types);
    }

    /**
     * 获取电影的所有区域不重复
     *
     * @return
     */
    @GetMapping("/countryList")
    public Result countryList() {

        return new Result(true, movieService.countryList());
    }

    /**
     * 获取电影的所有区域不重复
     *
     * @return
     */
    @GetMapping("/yearList")
    public Result yearList() {

        return new Result(true, movieService.yearList());
    }

    /**
     * 根据电影的分类查询
     */
    @GetMapping("/moviesByChoose")
    public Result moviesByChoose(String type, String area, String year,
                                 int showType, boolean order) {

        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.select("t_movie.*");

        StringBuilder lastSql = null;

        if (Constant.All.equals(type)) {
            //类型全部
            if (!Constant.All.equals(area)) {
                wrapper.eq("country", area);
            }
            if (!Constant.All.equals(year)) {
                wrapper.eq("year(release_time)", year);
            }

//            拼接影片放映类型
            if (showType == Constant.HOT_SHOWING) {

                wrapper.le("release_time", new Date());
                wrapper.ge("end_time", new Date());
            } else if (showType == Constant.COMING_SOON) {

                wrapper.gt("release_time", new Date());
            } else {

                wrapper.lt("end_time", new Date());
            }

            if (order) {
                wrapper.orderByDesc("score/comments_number");
            }
        } else {

            //类型筛选，需联表查询相应类型
            lastSql = new StringBuilder("join t_movietype mt on t_movie.movie_id = mt.movie_id" +
                    " where mt.type_id = " + "'" + type + "'");

            if (!Constant.All.equals(area)) {
                lastSql.append(" and country = ").append("'").append(area).append("'");
            }
            if (!Constant.All.equals(year)) {
                lastSql.append(" and year(release_time) = ").append("'").append(year).append("'");
            }

            //拼接电影放映时间分类类型
            if (showType == Constant.HOT_SHOWING) {

                lastSql.append(" and now() between release_time and end_time");
            } else if (showType == Constant.COMING_SOON) {

                lastSql.append(" and release_time > now()");
            } else {

                lastSql.append(" and end_time < now()");
            }

            if (order) {
                lastSql.append(" order by score/comments_number desc");
            }
        }

        if (lastSql != null) {
            wrapper.last(lastSql.toString());
        }

        List<Movie> movieList = movieService.list(wrapper);

        return new Result(movieList != null, movieList);
    }

    /**
     * 添加电影信息
     * @param movie 电影
     * @return result
     */
    @PostMapping("saveMovie")
    public Result saveMovie(@RequestBody Movie movie) {
        boolean flag = movieService.save(movie);
        return new Result(flag, movie.getMovieId());
    }

    @PutMapping("modifyMovie")
    public Result modifyMovie(@RequestBody Movie movie) {
        boolean flag = movieService.updateById(movie);
        return new Result(flag, flag?"信息修改完成！":"信息修改失败", movie);
    }

    /**
     * 电影媒体信息
     * @param img
     * @param previewImg
     * @param preview
     * @param movieId
     * @return
     */
    @PostMapping("saveMedia")
    public Result saveMedia(@RequestParam(value = "img", required = false) MultipartFile img,
                            @RequestParam(value = "previewImg", required = false) MultipartFile previewImg,
                            @RequestParam(value = "preview",required = false) MultipartFile preview,
                            @RequestParam(value = "movieId", required = false) String movieId) {
        Movie movie = new Movie();
        System.out.println("---------------------------------" + preview.getOriginalFilename());
        movie.setMovieId(movieId);
        boolean flag = false;
        try {
            flag = movieService.saveMedia(img, previewImg, preview, movie);
        } catch (IOException e) {
            System.out.println("媒体文件保存失败");
            e.printStackTrace();
        }
        return new Result(flag, flag ? "保存成功" : "保存失败", movie);
    }

    @GetMapping("listAllMovie/{currentPage}/{pageSize}")
    public Result listAllMovie(@PathVariable int currentPage, @PathVariable int pageSize, @RequestParam(required = false) String keyword, @RequestParam(required = false) String condition) {
        IPage<Movie> movieList = movieService.getAllMovie(currentPage, pageSize, keyword, condition);
        return new Result(true, movieList);
    }

    @GetMapping("movieList")
    public Result movieList(){
        List<Movie> movies = movieService.movieList();
        return new Result(true, movies);
    }

    @GetMapping("/getAllMovies")
    public Result getAllMovies(Integer currentPage,Integer pageSize,@RequestParam(required = false) String movieKeyword) {
        if (movieKeyword == null) {
            movieKeyword = "";
        }
        LambdaQueryWrapper<Movie> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Movie::getMovieName,movieKeyword).or().like(Movie::getEnglishName,movieKeyword).or().like(Movie::getDescribe,movieKeyword);
        Page<Movie> page = movieService.page(new Page<>(currentPage, pageSize), lambdaQueryWrapper);
        return new Result(page != null, page);
    }

    @GetMapping("/chart")
    public Result getChart() {
        QueryWrapper<Movie> movieQueryWrapper = new QueryWrapper<>();
        movieQueryWrapper.lt("release_time", new Date());
        movieQueryWrapper.gt("end_time", new Date());
        movieQueryWrapper.orderByDesc("daily_box_office");
        List<Movie> movies = movieService.list(movieQueryWrapper);
        return new Result(movies.size() != 0, movies);
    }

    @PutMapping("/upBoxOffice")
    public void upBoxOffice(Double up,String movieId){

        UpdateWrapper<Movie> wrapper = new UpdateWrapper<>();
        wrapper.last("set box_office = box_office+"+up+","+"daily_box_office" +
                "= daily_box_office+"+up+" where movie_id="+movieId);

        movieService.update(wrapper);
    }

    @GetMapping("/getDailyBoxOffice")
    public Result getDailyBoxOffice() {
        return new Result(true, movieService.getDailyBoxOffice());
    }

}

