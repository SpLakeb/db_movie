package com.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cinema.bean.Role;

import java.util.List;

/**
 * (Role)�����ӿ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
public interface RoleService extends IService<Role> {
    /**
     * 根据电影ID查询出电影角色相关信息
     * @param movieId
     * @return
     */
    List<Role> selectMovieRole(String movieId);
}

