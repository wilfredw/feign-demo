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


public class FeignApplication {
    private static final Logger logger = LoggerFactory.getLogger(FeignApplication.class);

    public static void main(String[] args) {
        HelloApi helloApi = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(HelloApi.class, "http://localhost:9730");
        ResultDTO resultDTO = helloApi.hello("this is from feign app.");
        logger.info("code:{},message:{},data:{}",resultDTO.getCode(),resultDTO.getMessage(),resultDTO.getData());
    }
}
