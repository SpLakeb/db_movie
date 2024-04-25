package com.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cinema.bean.Movietype;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (Movietype)�����ݿ���ʲ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Mapper
@Repository
public interface MovietypeMapper extends BaseMapper<Movietype> {

}

