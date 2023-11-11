package com.Mohamed.springService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NODE-SERVICE", url = "http://localhost:8001")
public interface NodeFeignClient {
    @PostMapping("/node/spring-data")
    String sendDataToNode(@RequestBody product produit);
}
