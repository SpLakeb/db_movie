package com.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cinema.bean.Performer;
import com.cinema.bean.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * (User)�����ӿ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:10
 */
public interface UserService extends IService<User> {

    IPage<User> getAllByPage(int currentPage, int pageSize);

    /**
     * 保存用户的图片
     * @param img
     * @param user
     * @return
     * @throws IOException
     */
    Boolean saveImg(MultipartFile img, User user) throws IOException;
}

