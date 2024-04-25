package com.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cinema.bean.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 28794
 * @ClassName AdminMapper.java
 * @since 2022-02-28 18:44
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
}
