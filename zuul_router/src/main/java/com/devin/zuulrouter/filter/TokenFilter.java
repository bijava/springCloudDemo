package com.devin.zuulrouter.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * Token 加权过滤器
 */
public class TokenFilter extends ZuulFilter {

    private static final String ACCESS_KEY = "ZZ";

    /**
     * 返回一个字符疮代表过滤器的类型
     * pre 表示被路由之前
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 过滤优先级
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.DEBUG_FILTER_ORDER;
    }

    /**
     * 是否执行当前过滤器
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 进入过滤器处理
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("accessToken Filter.........");

        // 获取Request上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String accessToken = String.valueOf(request.getParameter("Token"));
        System.out.println("accessToken Filter.........\t :"+request.getRequestedSessionId());
        // 设置路由状态，表示已经进行路由
        ctx.set("zuulTokenFilter", true); // 标识这个路由已经处理了
        if(!accessToken.equals(ACCESS_KEY)) {
            // 判断请求是否存在令牌，如果没有返回401
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        return null;
    }
}
