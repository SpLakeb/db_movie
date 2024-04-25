package com.cinema.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.bean.Admin;
import com.cinema.mapper.AdminMapper;
import com.cinema.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 28794
 * @ClassName AdminServiceImpl.java
 * @since 2022-02-28 18:45
 */
@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService{
    @Resource
    AdminMapper adminMapper;
}
