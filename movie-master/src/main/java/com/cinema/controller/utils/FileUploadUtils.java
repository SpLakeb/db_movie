package com.cinema.controller.utils;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUploadUtils {

    public static RestTemplate restTemplate = new RestTemplate();

    public static Boolean upload(String url, MultipartFile file, String filename) throws IOException {
        ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
            @Override
            public long contentLength() {
                return file.getSize();
            }
        };
        MultiValueMap<String, Object> multipartRequest = new LinkedMultiValueMap<>();
        multipartRequest.add("file", fileAsResource);
        multipartRequest.add("filename", filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(multipartRequest, headers);
        return restTemplate.postForObject(url, requestEntity, Boolean.class);
    }

    public static Boolean remove(String filename) {
        String url = "http://120.76.175.200:8083/remove";
        return restTemplate.getForObject(url + "?filename=" + filename, Boolean.class);
    }
}
