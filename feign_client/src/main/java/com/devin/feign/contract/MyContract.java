package com.devin.feign.contract;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.devin.feign.annotation.MyAnnott;
import feign.Contract.BaseContract;
import feign.MethodMetadata;

/**
 * 自定义翻译器
 * @author devin
 */
public class MyContract extends BaseContract {

    @Override
    protected void processAnnotationOnClass(MethodMetadata metadata,
                                            Class<?> arg1) {}

    @Override
    protected void processAnnotationOnMethod(MethodMetadata metadata,
                                             Annotation annotation, Method method) {

        //注解是MyAnnott类型，才解析
        if(MyAnnott.class.isInstance(annotation)){
            // 获取注解的实例
            MyAnnott myAnnott = method.getAnnotation(MyAnnott.class);
            // 获取服务的 url
            String url = myAnnott.url();
            // 获取配置的 HTTP 方法
            String httpMethod = myAnnott.method();
            // 将值设置到模板中
            metadata.template().method(httpMethod);
            metadata.template().append(url);
        }
    }

    @Override
    protected boolean processAnnotationsOnParameter(MethodMetadata metadata,
                                                    Annotation[] annotations, int i) {
        return false;
    }

}
