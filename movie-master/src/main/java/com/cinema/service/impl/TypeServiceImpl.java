package com.cinema.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.mapper.TypeMapper;
import com.cinema.bean.Type;
import com.cinema.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Type)�����ʵ����
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
@Service("typeService")
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Boolean delete(Integer typeId) {
        return typeMapper.deleteById(typeId) > 0;
    }

    @Override
    public Boolean modify(Type type) {
        return typeMapper.updateById(type) > 0;
    }

    @Override
    public Boolean insert(Type type) {
        return typeMapper.insert(type)>0;
    }

    @Override
    public Type get(Integer typeId) {
        return typeMapper.selectById(typeId);
    }

    @Override
    public List<Type> getTypeNameCount() {return typeMapper.getTypeNameCount();}
}

