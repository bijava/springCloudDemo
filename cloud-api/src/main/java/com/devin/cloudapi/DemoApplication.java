package com.devin.cloudapi;

import com.alibaba.fastjson.annotation.JSONField;
import com.devin.cloudapi.entity.Student;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@EnableEurekaClient
@SpringBootApplication
@RestController
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
//		SpringApplication.run(DemoApplication.class, args);

		//读取控制台输入，避免端口冲突
		Scanner scanner = new Scanner(System.in);

		String port = scanner.nextLine();

		new SpringApplicationBuilder(DemoApplication.class).properties(
				"server.port=" + port).run(args);
	}

	@GetMapping(value = "student_json", produces = MediaType.APPLICATION_JSON_VALUE)
	public List getStudentJson() {
		List<Student> students = new ArrayList<>();
		students.add(new Student("刘备", "1406001", 25));
		students.add(new Student("张飞", "1406001", 20));
		students.add(new Student("关羽", "1406001", 23));
		students.add(new Student("曹操", "1405001", 24));
		return students;
	}

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
