package com.devin.zuulrouter.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * 使用RestTemplate来调用集群服务
 *
 */
@RestController
public class RestTemplateFilter extends ZuulFilter {

    @Autowired
    private RestTemplate restTemplate;

//    public RestTemplateFilter(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    public boolean shouldFilter() {
        /*
         * HTTP请求的全部信息，都封装在一个RequestContext的对象中， 该对象继承ConcurrentHashMap，
         * 可以将RequestContext看作一个Map
         * RequestContext维护着当前线程的全部请求变量，例如请求的URI、serviceid、主机等信息
         * 以RequestContext为基础，编写一个自定义的过滤器，使用RestTemplate来调用集群服务
         */
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        // 获取请求URL
        String uri = request.getRequestURI();

        // && !"true".equals(ctx.get("zuulRestTemplateFilter")) -- 自行扩展内容
        // 为了不影响其他路由，uri中含有myServer才执行本路由
        if (uri.indexOf("myServer") != -1 ) {
            return true;
        } else {
            return false;
        }

    }

    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 获取需要调用的服务id
        String serviceId = (String) ctx.get("serviceId");

        // 获取请求的URI
        String uri = (String) ctx.get("requestURI");

        // 组合成URL给RestTemplated调用
        String url = "http://" + serviceId + uri;
        System.out.println("Come into RestTemplateFilter...，URL：" + url);

        // 调用并获取结果
        String result = restTemplate.getForObject(url, String.class);

        // 设置路由状态，表示已经进行路由
        // ctx.set("zuulRestTemplateFilter", true); // 标识这个路由已经处理了
        // 将结果设置进请求上下文中
        ctx.setResponseBody(result);

        // 设置响应标识
        ctx.sendZuulResponse();

        return null;
    }

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

}
