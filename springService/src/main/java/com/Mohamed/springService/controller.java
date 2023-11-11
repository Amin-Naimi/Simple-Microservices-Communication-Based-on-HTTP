package com.Mohamed.springService;

import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @PostMapping("/spring/authenticate")
    public String autheticate(@RequestBody model model ){
        System.out.println("Microservice Spring Boot : Utilisateur reçu - " + model.toString());
        return "User: "+ model.toString()+"authenticated successfully!";
    }
}
