package com.cinema.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.bean.Performer;
import com.cinema.bean.Type;
import com.cinema.controller.utils.FileUploadUtils;
import com.cinema.mapper.MovieMapper;
import com.cinema.bean.Movie;
import com.cinema.service.MovieService;
import com.cinema.vo.BoxOffice;
import org.apache.ibatis.binding.BindingException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * (Movie)�����ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Service("movieService")
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Resource
    private MovieMapper movieMapper;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 查询评分前10电影
     */
    @Override
    public List<Movie> topMovie() {
        return movieMapper.topMovie();
    }

    /**
     * 查询评分前100电影
     */
    @Override
    public List<Movie> allTopMovie() {
        return movieMapper.allTopMovie();
    }

    @Override
    public List<Type> movieType(String movieId) {return movieMapper.movieType(movieId);}

    @Override
    public List<String> countryList() {
        return movieMapper.countryList();
    }

    @Override
    public List<String> yearList() {
        return movieMapper.yearList();
    }

    @Override
    public Boolean saveMedia(MultipartFile img, MultipartFile previewImg, MultipartFile preview, Movie m) throws IOException {
        //设置上传url
        String url = "http://120.76.175.200:8083/upload";
        long time = new Date().getTime();

        Movie movie = movieMapper.selectById(m.getMovieId());

        Boolean flag1 = true;
        if (img != null) {
            if (movie.getImgUrl() != null) {
                //删除原文件
                String filename = movie.getImgUrl().substring(url.length() + 1);
                FileUploadUtils.remove(filename);
            }
            //获取文件
            String oldImgName = img.getOriginalFilename();
            //图片文件名
            int index1 = Objects.requireNonNull(oldImgName).lastIndexOf('.');
            String imgName = time + "_img_" +  movie.getMovieId() + oldImgName.substring(index1);
            //电影图片路径保存
            movie.setImgUrl(url + '/' + imgName);
            flag1 = FileUploadUtils.upload(url, img, imgName);
        }

        Boolean flag2 = true;
        if (previewImg != null) {
            if (movie.getPreviewImgUrl() != null) {
                String filename = movie.getPreviewImgUrl().substring(url.length() + 1);
                FileUploadUtils.remove(filename);
            }
            String oldPreviewImgName = previewImg.getOriginalFilename();
            int index2 = Objects.requireNonNull(oldPreviewImgName).lastIndexOf('.');
            String previewImgName = time + "_previewImg_" +  movie.getMovieId() + oldPreviewImgName.substring(index2);
            movie.setPreviewImgUrl(url + '/' + previewImgName);
            flag2 = FileUploadUtils.upload(url, previewImg, previewImgName);
        }

        Boolean flag3 = true;
        if (preview != null) {
            if (movie.getPreviewUrl() != null) {
                String filename = movie.getPreviewUrl().substring(url.length() + 1);
                FileUploadUtils.remove(filename);
            }

            String oldPreviewName = preview.getOriginalFilename();
            int index3 = Objects.requireNonNull(oldPreviewName).lastIndexOf('.');
            String previewName = time + "_preview_" +  movie.getMovieId() + oldPreviewName.substring(index3);
            movie.setPreviewUrl(url + '/' + previewName);
            flag3 = FileUploadUtils.upload(url, preview, previewName);
        }

        movieMapper.updateMediaById(movie);
        m.setImgUrl(movie.getImgUrl()).setPreviewImgUrl(movie.getPreviewImgUrl()).setPreviewUrl(movie.getPreviewUrl());

        return flag1 && flag2 && flag3;
    }

    @Override
    public IPage<Movie> getAllMovie(int currentPage, int pageSize, String keyword, String condition) {
        IPage<Movie> page = new Page<>(currentPage, pageSize);
        QueryWrapper<Movie> qw = new QueryWrapper<>();
        System.out.println(keyword);
        if (condition != null && !"".equals(condition)) {
            if ("score".equals(condition)) {
                qw.orderByDesc("(score / comments_number)");
            }
            else {
                qw.orderByDesc(condition);
            }
        }
        qw.like("country", keyword).or()
                        .like("movie_name", keyword).or()
                        .like("english_name", keyword).or()
                        .like("`describe`", keyword).or();
        movieMapper.selectPage(page, qw);
        return page;
    }

    @Override
    public List<Movie> movieList() {
        return movieMapper.selectList(null);
    }

    @Override
    public List<BoxOffice> getDailyBoxOffice() {
        return movieMapper.selectDailyBoxOffice();
    }


    @Override
    public List<Movie> getSuggestionsMovie(String keyWord) {
        return movieMapper.getSuggestionsMovie(keyWord);
    }
}

