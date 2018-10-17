package com.devin.zuulrouter;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import javax.annotation.PostConstruct;
import java.io.File;

@SpringBootApplication
@EnableZuulProxy//打开Zuul客户端功能，开启Zuul代理
@EnableEurekaClient//  Eureka 客户端
public class ZuulRouterApplication {

	// 动态加载过滤器 start，这是从Zuul官方源代码中Copy出来的
	@PostConstruct
	public void zuulInit() {
		FilterLoader.getInstance().setCompiler(new GroovyCompiler());
		// 会到groovy/filters默认目录下读取配置zuul.filter.root，获取脚本根目录
		String scriptRoot = System.getProperty("zuul.filter.root",
				"groovy/filters");
		// 获取刷新间隔，5s刷新一次
		String refreshInterval = System.getProperty(
				"zuul.filter.refreshInterval", "5");
		if (scriptRoot.length() > 0)
			scriptRoot = scriptRoot + File.separator;
		try {
			// 通过FilterFileManager设置GroovyFileFilter过滤器
			FilterFileManager.setFilenameFilter(new GroovyFileFilter());
			// 通过FilterFileManager的init进行Groovy的文件管理，
			// 参数说明：刷新时间、文件目录（因为我们有三个阶段，所以我们提供三个目录）
//			FilterFileManager.init(Integer.parseInt(refreshInterval),
//					scriptRoot + "pre", scriptRoot + "route", scriptRoot
//							+ "post");
		} catch (NullPointerException ex) {
			System.out.println("动态过滤器没有配置");
		}  catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// 动态加载过滤器 end

	public static void main(String[] args) {
		SpringApplication.run(ZuulRouterApplication.class, args);
	}
}
