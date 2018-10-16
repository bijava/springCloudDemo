package com.devin.hystrix.main;

import com.devin.hystrix.command.FallbackCommand;

/**
 * 断路器打开（断路器：想象成保险丝）
 * 回退方法
 */
public class FallbackMain {

    public static void main(String[] args) {
        FallbackCommand c = new FallbackCommand();
        String result = c.execute();
        System.out.println(result);
    }
}
