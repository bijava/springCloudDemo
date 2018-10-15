package com.devin.cloudapi.indicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;

/**
 * 健康检测处理器
 * @author Devin
 */
@Component
public class MyHealthCheckHandler implements HealthCheckHandler {

    @Autowired
    private MyHealthIndicator myHealthIndicator;

    //默认状态，每30S执行一次该方法，本人已经修改为10S
    public InstanceStatus getStatus(InstanceStatus currentStatus) {

        //先获取客户端的服务状态
        Status status=myHealthIndicator.health().getStatus();

        //将状态传播至服务器
        if(status.equals(Status.UP)){
            return InstanceStatus.UP;
        }else{
            return InstanceStatus.DOWN;
        }
    }

}
