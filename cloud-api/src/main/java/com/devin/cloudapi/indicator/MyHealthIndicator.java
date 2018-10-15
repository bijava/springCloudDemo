package com.devin.cloudapi.indicator;

import com.devin.cloudapi.controller.MyController;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * 健康指示器
 * @author Devin
 */
@Component
public class MyHealthIndicator implements HealthIndicator {

    public Health health() {

        //如果canVisitOb是true，返回健康状态：UP，否则返回健康状态：DOWN
        if(MyController.canVisitOb){
            return new Health.Builder(Status.UP).build();
        }else{
            return new Health.Builder(Status.DOWN).build();
        }
    }

}
