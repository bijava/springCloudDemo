package com.devin.eruakeclient.client;

import com.devin.eruakeclient.entity.Person;
import com.devin.eruakeclient.entity.Result;
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

}
