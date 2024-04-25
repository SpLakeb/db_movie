package com.cinema.service.impl;

import com.alibaba.fastjson.JSON;
import com.cinema.config.AlipayConfig;
import com.cinema.vo.AlipayBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

/**
 * 支付宝支付接口
 * @author 小道仙
 * @date 2020年2月18日
 */
@Component
public class Alipay {

    @Autowired
    private AlipayConfig alipayConfig;

    /**
     * 支付接口
     * @param alipayBean
     * @return
     * @throws AlipayApiException
     */
    public String pay(AlipayBean alipayBean) throws AlipayApiException {
        // 1、获得初始化的AlipayClient
        String serverUrl = alipayConfig.getGatewayUrl();
        String appId = alipayConfig.getAppId();
        String privateKey = alipayConfig.getPrivateKey();
        String format = "json";
        String charset = alipayConfig.getCharset();
        String alipayPublicKey = alipayConfig.getPublicKey();
        String signType = alipayConfig.getSignType();
        String returnUrl = alipayConfig.getReturnUrl();
        String notifyUrl = alipayConfig.getNotifyUrl();
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(returnUrl);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        // 封装参数

        String json = JSON.toJSONString(alipayBean);
        System.out.println(json);
        alipayRequest.setBizContent(json);
        // 3、请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // 返回付款信息
        return result;
    }
}
