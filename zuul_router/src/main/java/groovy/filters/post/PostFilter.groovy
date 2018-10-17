package groovy.filters.post

import com.netflix.zuul.ZuulFilter
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants

class PostFilter extends ZuulFilter {

    public boolean shouldFilter() {
        return true;
    }

    public Object run() {
        System.out.println("Come into PostFilter...");
        return null;
    }

    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    public int filterOrder() {
        return 3;
    }
}