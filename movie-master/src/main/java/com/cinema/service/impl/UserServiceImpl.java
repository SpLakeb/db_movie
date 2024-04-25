package com.cinema.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.bean.Performer;
import com.cinema.controller.utils.FileUploadUtils;
import com.cinema.mapper.UserMapper;
import com.cinema.bean.User;
import com.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

import javax.annotation.Resource;

/**
 * (User)�����ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:10
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public IPage<User> getAllByPage(int currentPage, int pageSize) {
        IPage<User> iPage = new Page<>(currentPage, pageSize);
        return userMapper.selectPage(iPage, null);
    }

    @Override
    public Boolean saveImg(MultipartFile img, User user) throws IOException {
        //设置上传url
        String url = "http://120.76.175.200:8083/upload";
        String oldName = img.getOriginalFilename();
        int index = Objects.requireNonNull(oldName).lastIndexOf('.');
        //图片文件名
        String filename = new Date().getTime() + "_" +  user.getUserId() + oldName.substring(index);
        //演员图片路径保存
        user.setAvatar(url + '/' + filename);
        updateById(user);

        return FileUploadUtils.upload(url, img, filename);
    }
}

