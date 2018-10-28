package com.devin.config.entity;

/**
 * 懒汉式单例内部类
 * 线程锁，（线程安全），效率高
 */
public class Person {
    String name;
//    private static Person p = null;
//
//    public Person() {}
//
//    public static Person getInstance() {
//        if (p == null) {
//            synchronized (Person.class) {
//                if (p == null) p = new Person();
//            }
//        }
//        return p;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
