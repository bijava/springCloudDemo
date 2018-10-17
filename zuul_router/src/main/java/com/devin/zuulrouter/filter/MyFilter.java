package com.devin.zuulrouter.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;

public class MyFilter extends ZuulFilter {

    /**
     * 过滤器配置完成之后，还需要告知Spring容器这个过滤器的存在
     */

    /**
     * 返回false/true，判断是否执行MyFilter过滤器
     * <p>
     * 决定该过滤器是否要执行
     */
    public boolean shouldFilter() {
        /*
         * 如果A过滤器执行过一次，就将外部值设置为“我已经执行过一次转发”，
         * 下一个过滤器B则会根据这个值进行校验，是否能继续执行B过滤器(“自己要不要执行？”)，
         */
        // return false;
        return true;// 为了测试，我们将其设置为总是执行
    }

    public Object run() {
        System.out.println("Come into MyFilter...");
        return null;
    }

    /**
     * 过滤器中有多个阶段，通过该方法，设置自定义过滤器所处阶段
     */
    @Override
    public String filterType() {
        // 设置为"routing"阶段
        return FilterConstants.ROUTE_TYPE;
    }

    /**
     * 设置优先级
     */
    @Override
    public int filterOrder() {
        // 从过滤器优先级的图可知，RibbonRoutingFilter为10，SimpleHostRoutingFilter为100，SendForwardFilter为500
        // 将优先级设置为最高，<10即可
        return 1;
    }

}
