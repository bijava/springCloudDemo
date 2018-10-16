package com.devin.cloudapi.controller;

import com.devin.cloudapi.entity.Person;
import com.devin.cloudapi.entity.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@RestController
public class MyController {

    //能否访问数据库
    public static boolean canVisitOb = true;

    @RequestMapping(value = "/db/{can}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void setOb(@PathVariable boolean can){
        this.canVisitOb = can;
    }

    @PostMapping("/person/create")
    @ResponseBody
    public String createPerson(@RequestBody Person person) {
        System.out.println(person.getName() + "---" + person.getAge());
        return "创建成功：" + person.getId();
    }

    @RequestMapping(value = "/person/createXML", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_XML_VALUE,
            produces = MediaType.APPLICATION_XML_VALUE)
    public String createXMLPerson(@RequestBody Person person) {
        System.out.println(person.getName() + "-" + person.getId());
        return "<result><message>success</message></result>";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Student findStudent(){
        Student student = new Student();
        student.setAddress("11231231");
        student.setName("莉香");
        student.setAge(21);
        student.setCreateTime(new Date());
        return student;
    }

    @RequestMapping(value = "/student/{studentNo}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Student findStudent(@PathVariable("studentNo")Integer studentNo, HttpServletRequest request){
        Student student = new Student();
        student.setAddress("11231231");
        student.setName("莉香"+request.getRequestURL().toString());
        student.setAge(21);
        student.setCreateTime(new Date());
        student.setNo(studentNo);
        return student;
    }

    @RequestMapping("hi")
    public String hi() {
        return "hi spring boot";
    }

    @RequestMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public HashMap helloWord() {
        HashMap map = new HashMap();
        map.put("name","devin");
        map.put("age","28");
        map.put("sex","男");
        return map;
    }

    @GetMapping("/errorHello")
    public String erroeHello(HttpServletRequest httpServletRequest)
            throws InterruptedException {
        // 模拟需要处理10秒
        Thread.sleep(10000);
        return "Error Hello world";
    }
}
