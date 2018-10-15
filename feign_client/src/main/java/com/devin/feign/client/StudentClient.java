package com.devin.feign.client;

import feign.Param;
import feign.RequestLine;

import java.util.Date;

public interface StudentClient {

    @RequestLine("GET /student/{studentNo}")
    Student findStudent(@Param("studentNo") Integer studentNo);

    class Student {
        //姓名
        String name;
        //年龄
        int age;
        //住址
        String address;
        //创建时间
        Date createTime;
        //编号
        int no;

        //省略setter、getter

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }
    }

}
