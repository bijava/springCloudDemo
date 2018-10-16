package com.devin.hystrix.main;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;

public class CommandRunMain {

    public static void main(String[] args) throws Exception {
        RunCommand c1 = new RunCommand("observe method");
        c1.observe();

        RunCommand c2 = new RunCommand("toObservable method");
        // 只返回一个可观察的对象，并不会马上执行
        // 只有当Observable真正被订阅之后，才会执行
        Observable ob = c2.toObservable();

        // 执行订阅，则会开始执行
        ob.subscribe(new Observer<String>() {

            // 命令执行完成之后
            public void onCompleted() {
                System.out.println("命令执行完成之后...");
            }

            // 命令有错误
            public void onError(Throwable e) {

            }

            // 命令执行返回
            public void onNext(String t) {
                System.out.println("执行返回：" + t);
            }

        });

        Thread.sleep(1000);
    }

    static class RunCommand extends HystrixCommand<String> {

        String msg;

        public RunCommand(String message) {
            super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
            this.msg = message;
        }

        @Override
        protected String run() throws Exception {
            System.out.println(msg);
            return "success";
        }

    }
}
