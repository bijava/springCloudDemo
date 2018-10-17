package groovy.filters;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;

class RouteFilter extends ZuulFilter {

    public boolean shouldFilter() {
        return true;
    }

    public Object run() {
        System.out.println("Come into RouteFilter...");
        return null;
    }

    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    public int filterOrder() {
        return 3;
    }
}