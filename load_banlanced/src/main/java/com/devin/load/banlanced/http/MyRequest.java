package com.devin.load.banlanced.http;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;

public class MyRequest implements HttpRequest {

    private HttpRequest sourceRequest;

    public MyRequest(HttpRequest souHttpRequest) {
        this.sourceRequest = souHttpRequest;
    }

    @Override
    public HttpHeaders getHeaders() {
        return sourceRequest.getHeaders();
    }

    @Override
    public HttpMethod getMethod() {
        return sourceRequest.getMethod();
    }

    @Override
    public String getMethodValue() {
        return sourceRequest.getMethodValue();
    }

    /**
     * 将URI转化
     */
    @Override
    public URI getURI() {
        try {
            String oldUri = sourceRequest.getURI().toString();
            URI newUri = new URI("http://localhost:8080/hello");
            return newUri;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sourceRequest.getURI();
    }

}
