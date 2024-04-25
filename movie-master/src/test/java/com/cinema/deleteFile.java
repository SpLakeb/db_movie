package com.cinema;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class deleteFile {

    @Resource
    private RestTemplate restTemplate;

//    @Test
//    void deleteFile() {
//        String url = "http://120.76.175.200:8083/remove";
//        String filename = "1646663758882_img_1500842689987084289.jpg";
//
//        Boolean flag = restTemplate.getForObject(url + "?filename=" + filename, Boolean.class);
//        System.out.println(flag);
//    }
}
