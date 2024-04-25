package com.cinema.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cinema.bean.Expect;
import com.cinema.mapper.ExpectMapper;
import com.cinema.service.ExpectService;
import org.springframework.stereotype.Service;

/**
 * @author 28794
 * @ClassName ExpectServiceImpl.java
 * @since 2022-03-05 11:05
 */
@Service("expectService")
public class ExpectServiceImpl extends ServiceImpl<ExpectMapper, Expect> implements ExpectService {
}
