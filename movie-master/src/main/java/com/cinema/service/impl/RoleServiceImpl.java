package com.cinema.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.mapper.RoleMapper;
import com.cinema.bean.Role;
import com.cinema.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Role)�����ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectMovieRole(String movieId) {
        return roleMapper.selectMovieRole(movieId);
    }
}

