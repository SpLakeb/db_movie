package com.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cinema.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (User)�����ݿ���ʲ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:10
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}

