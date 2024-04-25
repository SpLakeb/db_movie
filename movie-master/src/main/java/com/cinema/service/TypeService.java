package com.cinema.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cinema.bean.Type;

import java.util.List;

/**
 * (Type)�����ӿ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
public interface TypeService extends IService<Type> {

    Boolean delete(Integer typeId);
    Boolean modify(Type type);
    Boolean insert(Type type);
    Type get(Integer typeId);
    /**
     * 查询票房前十的电影类型
     * @return
     */
    List<Type> getTypeNameCount();

}

