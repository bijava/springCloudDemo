package com.devin.cloudapi.entity;

import java.util.Date;

/**
 *
 */
public class Student {
    //姓名
    private String name;
    //年龄
    private int age;
    //住址
    private String address;
    //创建时间
    private Date createTime;
    //编号
    private int no;

    public Student() {
        super();
    }
    public Student(String name, String address, int age) {
        super();
        this.name = name;
        this.age = age;
        this.address = address;
    }

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
    @Override
    public String toString() {
        return  createTime != null ? "Student [name=" + name + ", age=" + age + ", address="
                + address + ", createTime= "+ createTime + "]" :
                "Student [name=" + name + ", age=" + age + ", address="
                        + address + "]";
    }
}
