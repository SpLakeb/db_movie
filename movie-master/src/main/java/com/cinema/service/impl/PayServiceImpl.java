package com.cinema.service.impl;

import com.cinema.service.PayService;
import com.cinema.vo.AlipayBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;

@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private Alipay alipay;

    @Override
    public String aliPay(AlipayBean alipayBean) throws AlipayApiException {
        return alipay.pay(alipayBean);
    }
}
