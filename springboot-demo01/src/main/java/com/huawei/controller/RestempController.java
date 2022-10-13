package com.huawei.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author king
 * @date 2022/9/5-23:41
 * @Desc
 */
@RestController
public class RestempController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/post/rest")
    public ResponseEntity testRest(@RequestBody Map<String, Object> map) {
        String url  = "http://localhost:8080/add";
        HttpHeaders headers = new HttpHeaders();
        headers.add("refer", "http://localhost:8080");
        HttpEntity<Object> httpEntity = new HttpEntity<>(map, headers);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, Object.class);
    }

    @GetMapping("/get/rest")
    public ResponseEntity getRest(@RequestBody Map<String, Object> map) {
        String url = "http://localhost:8080/query";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Gson gson = new Gson();
        HttpEntity<Object> httpEntity = new HttpEntity<>(gson.toJson(map), headers);
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object.class);
    }
}
