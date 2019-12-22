package com.wei.feign.app.feign.api;

import com.wei.feign.app.common.model.dto.ResultDTO;
import feign.*;

import java.util.Map;

public interface HelloApi {

    // RequestLine注解声明请求方法和请求地址,可以允许有查询参数
    @RequestLine("GET /hello/hello")
    ResultDTO hello(@Param("message") String message);

    @RequestLine("POST /hello/postall")
    ResultDTO postAll(@Param("message") String message,
                      @QueryMap() Map<String, String> paramMap,
                      @HeaderMap() Map<String, String> headMap);

}
