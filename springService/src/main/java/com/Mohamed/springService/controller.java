package com.Mohamed.springService;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @PostMapping("/spring/authenticate")
    public String autheticate(@RequestBody model model) {
        System.out.println("Microservice Spring Boot Repond: Utilisateur reçu - " + model.toString());
        return "Microservice Spring Boot Repond: Utilisateur reçu : User: " + model.toString() + "authenticated successfully!";
    }


    /**OpenFeign**/
    @Autowired
    private NodeFeignClient nodeFeignClient;

    @PostMapping("/spring/sendDataToNode")
    public String sendDataToNode(@RequestBody product productData) {
        return nodeFeignClient.sendDataToNode(productData);
    }

    /**
    * Avec OpenFeign, vous n'avez pas à écrire manuellement la logique de l'appel HTTP, cela est géré par la bibliothèque.
    * Cela rend le code plus propre et plus lisible. Notez que NodeFeignClient est une interface, et Feign génère automatiquement
    * une implémentation pour vous. Assurez-vous simplement que le nom de votre service correspond à celui défini dans l'annotation @FeignClient.*/

}