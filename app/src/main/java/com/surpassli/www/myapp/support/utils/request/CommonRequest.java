package com.surpassli.www.myapp.support.utils.request;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;

import java.util.Map;

/**
 * Created by SurpassLi on 2017/1/10.
 */
public class CommonRequest {

    /**
     * get方法，拼接url
     * @param url
     * @param params
     * @return
     */
    public static Request createGetRequest(String url,RequestParams params){
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if(params != null){
            for(Map.Entry<String,String> entry : params.urlParams.entrySet()){
               urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0,urlBuilder.length() - 1)).get().build();
    }

    /**
     * post方法
     */
//    public static Request createPostRequest(String url,RequestParams params){
//        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
//        if (params != null) {
//            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
//                mFormBodyBuild.add(entry.getKey(), entry.getValue());
//            }
//        }
//        FormBody mFormBody = mFormBodyBuild.build();
//        return new Request.Builder().url(url).post(mFormBody).build();
//    }

    /**
     * 文件上传
     */

    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");

//    public static Request createMultiPostRequest(String url, RequestParams params) {
//
//        MultipartBody.Builder requestBody = new MultipartBody.Builder();
//        requestBody.setType(MultipartBody.FORM);
//        if (params != null) {
//
//            for (Map.Entry<String, Object> entry : params.fileParams.entrySet()) {
//                if (entry.getValue() instanceof File) {
//                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
//                            RequestBody.create(FILE_TYPE, (File) entry.getValue()));
//                } else if (entry.getValue() instanceof String) {
//
//                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
//                            RequestBody.create(null, (String) entry.getValue()));
//                }
//            }
//        }
//        return new Request.Builder().url(url).post(requestBody.build()).build();
//    }
}
