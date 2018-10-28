package com.devin.eruakeclient.controller;

import com.devin.eruakeclient.entity.Person;
import com.devin.eruakeclient.service.PersonService;
import com.devin.eruakeclient.service.PersonService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
@RefreshScope
public class MyRouterController {

    @Autowired
    private PersonService personService;

    @Autowired
    private Person person;

    @RequestMapping(value = "/person", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person router() {
        return personService.findPerson(2);
    }

    /**
     * 刷新Bean的示例
     * @return
     */
    @GetMapping("/personName")
    public String readPersonName() {
        return person.getName();
    }
}
