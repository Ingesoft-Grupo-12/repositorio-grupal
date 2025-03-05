package com.union.unionbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Clase principal que inicia la aplicación Spring Boot.
 */

@SpringBootApplication
@EnableJpaAuditing
public class UnionBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(UnionBackendApplication.class, args);
  }

}
