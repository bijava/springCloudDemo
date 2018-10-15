package com.devin.feign.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

public interface PersonClient {

    @RequestLine("GET /person/{personId}")
    Person findPerson(@Param("personId") Integer personId);

    @RequestLine("POST /person/create")
    @Headers("Content-Type: application/json")
    String createPerson(Person person);

    @RequestLine("POST /person/createXML")
    @Headers("Content-Type: application/xml")
    Result createPersonXML(Person person);

    @XmlRootElement
    class Person {

        @XmlElement
        Integer id;

        @XmlElement
        String name;

        @XmlElement
        Integer age;

        @XmlTransient
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @XmlTransient
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @XmlTransient
        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    @XmlRootElement
    class Result {
        @XmlElement
        String message;

        @XmlTransient
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
