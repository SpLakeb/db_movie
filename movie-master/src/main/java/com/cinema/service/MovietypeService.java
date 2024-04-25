package com.cinema.service;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cinema.bean.Movietype;

/**
 * (Movietype)�����ӿ�
 *
 * @author makejava
 * @since 2022-02-22 17:05:09
 */
public interface MovietypeService extends IService<Movietype> {


    Boolean delete(String movieId);

    Boolean insert(Movietype movietype);

    Boolean modify(Movietype movietype);

    Movietype get(String movieId);


}

