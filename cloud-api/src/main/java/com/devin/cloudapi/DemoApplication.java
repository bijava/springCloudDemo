package com.devin.cloudapi;

import com.alibaba.fastjson.annotation.JSONField;
import com.devin.cloudapi.entity.Student;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableEurekaClient
@SpringBootApplication
@RestController
// 打开断路器
@EnableCircuitBreaker
public class DemoApplication{

//	private static Logger logger = Logger.getLogger(String.valueOf(DemoApplication.class));

	@JSONField(format = "yyyy-MM-dd HH:mm")
	private Date createTime;

//	@Bean
//	public HttpMessageConverters fastJsonHttpMessageConverters(){
//		//1.需要定义一个convert转换消息的对象;
//		FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//		//2:添加fastJson的配置信息;
//		FastJsonConfig fastJsonConfig = new FastJsonConfig();
//		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//		//3处理中文乱码问题
//		List<MediaType> fastMediaTypes = new ArrayList<>();
//		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//		//4.在convert中添加配置信息.
//		fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
//		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//		HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
//		return new HttpMessageConverters(converter);
//	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		//读取控制台输入，避免端口冲突
//		System.out.print("服务启动>>>>>>\n\t请输入启动的port:");
//		Scanner scanner = new Scanner(System.in);
//
//		String port = scanner.nextLine();
//
//		new SpringApplicationBuilder(DemoApplication.class).properties(
//				"server.port=" + port).run(args);
	}

	/**
	 *  commandKey：之前用作缓存 ，
	 *  groupKey：用于执行线程，
	 *  重要的是超时时间的配置，
	 *  线程池的配置
	 */
	@HystrixCommand(
			groupKey = "StudentGroupKey",
			commandKey = "StudentCommandKey",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
			},
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "2")
			})
	@GetMapping(value = "student_json", produces = MediaType.APPLICATION_JSON_VALUE)
	public List getStudentJson() {
		List<Student> students = new ArrayList<>();
		students.add(new Student("刘备", "1406001", 25));
		students.add(new Student("张飞", "1406001", 20));
		students.add(new Student("关羽", "1406001", 23));
		students.add(new Student("曹操", "1405001", 24));
		return students;
	}

	@HystrixCommand
	@RequestMapping(value = "getStudent", produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudent(){
		Student student = new Student();
		student.setAddress("11231231");
		student.setName("莉香");
		student.setAge(21);
		student.setCreateTime(new Date());
		return student;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
