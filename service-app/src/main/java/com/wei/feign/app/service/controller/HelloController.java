package com.wei.feign.app.service.controller;

import com.wei.feign.app.common.model.dto.ResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;

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


    @RequestMapping(value = "/getall", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultDTO getAll(HttpServletRequest request){
        ResultDTO returnValue = new ResultDTO();
        returnValue.setCode(0);
        returnValue.setMessage("success.");
        returnValue.setData("Hello! This is getAll Request!");
        System.out.println("=============getAll Process======= " + request.getMethod());


        Map<String,String[]> requestMsg = request.getParameterMap();
        Enumeration<String> requestHeader = request.getHeaderNames();

        System.out.println("------- header -------");
        while(requestHeader.hasMoreElements()){
            String headerKey=requestHeader.nextElement().toString();
            //打印所有Header值

            System.out.println(headerKey+" : "+request.getHeader(headerKey));
        }

        System.out.println("------- parameter -------");
        for(String key :requestMsg.keySet())
        {
            for(int i=0;i<requestMsg.get(key).length;i++)
            {
                //打印所有请求参数值

                System.out.println(key+" : "+requestMsg.get(key)[i].toString());
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        return returnValue;
    }

    @RequestMapping(value = "/postall", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResultDTO postAll(HttpServletRequest request){
        ResultDTO returnValue = new ResultDTO();
        returnValue.setCode(0);
        returnValue.setMessage("success.");
        returnValue.setData("Hello! This is postAll Request!");
        System.out.println("=============postAll Process=======" + request.getMethod());


        Map<String,String[]> requestMsg = request.getParameterMap();
        Enumeration<String> requestHeader = request.getHeaderNames();
        InputStream io = null;
        String body;
        System.out.println("------- body -------");
        try{
            io = request.getInputStream();
            body = isToStr(io);
            //打印BODY内容
            System.out.println(body);
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("------- header -------");
        while(requestHeader.hasMoreElements()){
            String headerKey=requestHeader.nextElement().toString();
            //打印所有Header值

            System.out.println(headerKey+" : "+request.getHeader(headerKey));
        }

        System.out.println("------- parameters -------");
        for(String key :requestMsg.keySet())
        {
            for(int i=0;i<requestMsg.get(key).length;i++)
            {
                //打印所有请求参数值
                System.out.println(key+" : "+requestMsg.get(key)[i].toString());
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        return returnValue;
    }

    public static String isToStr(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        StringBuffer stringBuffer = new StringBuffer();
        while(is.read(buffer) >= 0) {
            stringBuffer.append(new String(buffer, "UTF-8"));
        }
        return stringBuffer.toString();
    }

}
