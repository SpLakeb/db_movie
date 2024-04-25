package com.cinema.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.bean.Movie;
import com.cinema.controller.utils.FileUploadUtils;
import com.cinema.mapper.PerformerMapper;
import com.cinema.bean.Performer;
import com.cinema.service.PerformerService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.annotation.Resource;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * (Performer)�����ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Service("performerService")
public class PerformerServiceImpl extends ServiceImpl<PerformerMapper, Performer> implements PerformerService {

    @Autowired
    PerformerMapper performerMapper;

    @Resource
    RestTemplate restTemplate;

    @Override
    public Long getBoxOffice(String performerId) {
        return performerMapper.getBoxOffice(performerId);
    }

    @Override
    public List<Movie> getMasterpiece(String performerId) {
        return performerMapper.getMasterpiece(performerId);
    }

    @Override
    public List<Movie> getAllMovies(String performerId) {
        return performerMapper.getAllMovies(performerId);

    }

    @Override
    public Boolean saveImg(MultipartFile img, Performer performer) throws IOException {
        //设置上传url
        String url = "http://120.76.175.200:8083/upload";
        String oldName = img.getOriginalFilename();
        int index = Objects.requireNonNull(oldName).lastIndexOf('.');
        //图片文件名
        String filename = new Date().getTime() + "_" +  performer.getPerformerId() + oldName.substring(index);
        //演员图片路径保存
        performer.setImgUrl(url + '/' + filename);
        updateById(performer);

        return FileUploadUtils.upload(url, img, filename);
    }

    @Override
    public IPage<Performer> getAllByPage(int currentPage, int pageSize, String keyword) {
        IPage<Performer> iPage = new Page<>(currentPage, pageSize);
        System.out.println(123 + "---------------------------------------");
        LambdaQueryWrapper<Performer> lqw = new LambdaQueryWrapper<>();
        //找到与关键字相似的名字，英文名，描述对应的演员
        lqw.like(Performer::getName, keyword).or().like(Performer::getEnglishName, keyword).or().like(Performer::getDescription, keyword);
        performerMapper.selectPage(iPage, lqw);
        return iPage;
    }

    @Override
    public List<Performer> getSuggestionsPerformer(String keyWord) {
        return performerMapper.getSuggestionsPerformer(keyWord);
    }
}

