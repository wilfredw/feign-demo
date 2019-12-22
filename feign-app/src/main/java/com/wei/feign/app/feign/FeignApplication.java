package com.wei.feign.app.feign;

import com.wei.feign.app.common.model.dto.ResultDTO;
import com.wei.feign.app.feign.api.HelloApi;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;


public class FeignApplication {
    private static final Logger logger = LoggerFactory.getLogger(FeignApplication.class);

    public static void main(String[] args) {
        HelloApi helloApi = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(HelloApi.class, "http://localhost:9730");
        ResultDTO resultDTO = helloApi.hello("this is from feign app.");
        logger.info("code:{},message:{},data:{}",resultDTO.getCode(),resultDTO.getMessage(),resultDTO.getData());
        String msg = "this is from feign app for postAll.";
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("x-header1","111");
        headerMap.put("x-header2","222");
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("key1","aaa");
        paramMap.put("key2","bbb");
        resultDTO = helloApi.postAll(msg, paramMap, headerMap);
        logger.info("code:{},message:{},data:{}",resultDTO.getCode(),resultDTO.getMessage(),resultDTO.getData());
    }
}
