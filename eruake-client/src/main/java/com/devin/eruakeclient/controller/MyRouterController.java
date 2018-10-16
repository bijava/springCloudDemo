package com.devin.eruakeclient.controller;

import com.devin.eruakeclient.entity.Person;
import com.devin.eruakeclient.service.PersonService;
import com.devin.eruakeclient.service.PersonService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class MyRouterController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/person", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person router() {
        return personService.findPerson(2);
    }

}
