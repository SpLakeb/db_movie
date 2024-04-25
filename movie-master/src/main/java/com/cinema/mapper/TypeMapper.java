package com.cinema.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cinema.bean.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Type)�����ݿ���ʲ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Mapper
@Repository
public interface TypeMapper extends BaseMapper<Type> {
    /**
     * 查询电影票房最高的前十位的类型
     * @return
     */
    List<Type> getTypeNameCount();
}

