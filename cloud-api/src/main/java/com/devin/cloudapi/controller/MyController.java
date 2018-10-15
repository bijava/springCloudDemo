package com.devin.cloudapi.controller;

import com.devin.cloudapi.entity.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class MyController {

    //能否访问数据库
    public static boolean canVisitOb = true;

    @RequestMapping(value = "/db/{can}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void setOb(@PathVariable boolean can){
        this.canVisitOb = can;
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
}
