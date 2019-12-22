package com.wei.feign.app.service.controller;

import com.wei.feign.app.common.model.dto.ResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    @ResponseBody
    public ResultDTO hello(String message) {
        logger.info("hello, receive {}", message);
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(1);
        resultDTO.setMessage("success!");
        resultDTO.setData("hi");
        return resultDTO;
    }
}
