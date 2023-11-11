package com.Mohamed.springService;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class controller {

    @PostMapping("/spring/authenticate")
    public String autheticate(@RequestBody model model) {
        System.out.println("Microservice Spring Boot Repond: Utilisateur reçu - " + model.toString());
        return "Microservice Spring Boot Repond: Utilisateur reçu : User: " + model.toString() + "authenticated successfully!";
    }


    /**
     * Using OpenFeign
     **/
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

    /*-----------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * Using RestTemplate
     **/

    @PostMapping("/spring/sendDataToNodeV1")
    public ResponseEntity<String> sendDataToNodeV1(@RequestBody product teste) {
        product dataToSend = teste;
        String nodeEndpointUrl = "http://localhost:8001/node/spring-data";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(nodeEndpointUrl, dataToSend, String.class);

        String nodeResponse = responseEntity.getBody();
        return ResponseEntity.ok("Spring send data. Node.js response: " + nodeResponse);
    }
    /*-----------------------------------------------------------------------------------------------------------------------------------------------------------------*/


    /*
 * Le choix entre OpenFeign, OkHttp, HttpClient et RestTemplate dépend souvent des besoins spécifiques de votre application et de vos préférences.
 Voici quelques points à considérer pour vous aider à prendre une décision :

**RestTemplate :
Avantages : Fait partie intégrante de l'écosystème Spring, facilite l'intégration avec d'autres composants Spring.
Inconvénients : Moins moderne et réactive que WebClient, nécessite plus de configuration pour les fonctionnalités avancées.

**WebClient (Spring WebFlux) :
Avantages : Réactif, prend en charge la programmation réactive, intégré à Spring WebFlux.
Inconvénients : Plus complexe pour les scénarios simples, nécessite une compréhension de la programmation réactive.

**OpenFeign :
Avantages : Déclaratif, facilite la création d'API clientes avec des annotations, intégré avec Spring Cloud pour la gestion de la découverte de services.
Inconvénients : Peut ne pas être aussi flexible que d'autres options dans certains scénarios, dépendance supplémentaire.

**OkHttp :
Avantages : Simple à utiliser, moderne, efficace, offre une API fluide.
Inconvénients : N'est pas directement intégré à l'écosystème Spring, nécessite une configuration supplémentaire pour l'intégration.

**HttpClient (Apache) :
Avantages : Bien établi, offre de nombreuses fonctionnalités, largement utilisé.
Inconvénients : Peut sembler un peu verbeux pour des cas d'utilisation simples, nécessite une configuration plus détaillée.
Si vous êtes déjà dans un environnement Spring, OpenFeign ou RestTemplate peuvent être des choix naturels en raison de leur intégration
transparente avec l'écosystème Spring. Si vous cherchez quelque chose de plus moderne et réactif, WebClient pourrait être une option.
OkHttp est une excellente bibliothèque indépendante qui peut être utilisée dans divers contextes.

Dans tous les cas, assurez-vous de considérer les besoins spécifiques de votre application, tels que la facilité d'utilisation, la performance,
la gestion des erreurs, la programmation réactive, etc.*/
}
