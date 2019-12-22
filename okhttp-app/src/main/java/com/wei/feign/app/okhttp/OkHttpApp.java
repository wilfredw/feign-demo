package com.wei.feign.app.okhttp;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OkHttpApp {

        public static void main(String[] args) {
            callHello();
        }

        public static void callHello() {
            Map<String, String> headers = new HashMap<>();
            Map<String, String> params = new HashMap<>();
            params.put("message", "this is from okhttp app.");
            try {
                String response = doPost("http://localhost:9730/hello/hello", headers, params);
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**用于发送post请求的方法*/
        public static String doPost(String url, Map<String, String> headers, Map<String, String> params) throws IOException {

            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            FormBody.Builder formBody = new FormBody.Builder();
            for (Map.Entry<String,String> e :
                    params.entrySet()) {
                formBody.add(e.getKey(), e.getValue());
            }
            FormBody body = formBody.build();
            Request request =  new Request.Builder()
                    .url(url)
                    .headers(Headers.of(headers))
                    .post(body)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            String resppnseString = response.body().string();
            System.out.println(resppnseString);
            return resppnseString;
        }


}
