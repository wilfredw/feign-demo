package com.wei.feign.app.feign.api;

import com.wei.feign.app.common.model.dto.ResultDTO;
import feign.Param;
import feign.RequestLine;

public interface HelloApi {

    // RequestLine注解声明请求方法和请求地址,可以允许有查询参数
    @RequestLine("GET /hello/hello")
    ResultDTO hello(@Param("message") String message);

}
