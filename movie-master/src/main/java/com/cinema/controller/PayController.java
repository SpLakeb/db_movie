package com.cinema.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.cinema.config.AlipayConfig;
import com.cinema.service.PayService;
import com.cinema.vo.AlipayBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 支付宝沙箱测试
 *
 * @author 小道仙
 * @date 2020年2月17日
 */
@RestController
@RequestMapping("/api/alipay")
public class PayController {

    @Autowired
    private PayService payService;

    @Autowired
    private AlipayConfig alipayConfig;

    /**
     * 阿里支付
     * @return
     * @throws AlipayApiException
     */
    @PostMapping( "/pay")
    public String alipay(@RequestBody AlipayBean alipayBean) throws AlipayApiException {
        return payService.aliPay(alipayBean);
    }

    @GetMapping("/searchPay")
    public boolean searchPay(String orderId){

        String serverUrl = alipayConfig.getGatewayUrl();
        String appId = alipayConfig.getAppId();
        String privateKey = alipayConfig.getPrivateKey();
        String format = "json";
        String charset = alipayConfig.getCharset();
        String alipayPublicKey = alipayConfig.getPublicKey();
        String signType = alipayConfig.getSignType();

        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "  \"out_trade_no\":"+orderId+"," +
                "  \"trade_no\":\"\"," +
                "  \"query_options\":[" +
                "    \"sub_msg\"" +
                "  ]" +
                "}");
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response.isSuccess()){
            String body = response.getBody();
            System.out.println(body);
            return true;
        } else {
            return false;
        }
    }

}
