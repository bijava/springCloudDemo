package groovy.filters.pre

import com.netflix.zuul.ZuulFilter
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants

class PreFilter extends ZuulFilter {

    public boolean shouldFilter() {
        return true;
    }

    public Object run() {
        System.out.println("Come into PreFilter...");
        return null;
    }

    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    public int filterOrder() {
        return -8;
    }
}