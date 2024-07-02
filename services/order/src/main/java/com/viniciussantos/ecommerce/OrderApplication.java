package com.viniciussantos.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Habilita o uso de JPA Auditing para preencher automaticamente campos de auditoria em entidades JPA
@SpringBootApplication
@EnableFeignClients // Habilita o uso de Feign Clients
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
