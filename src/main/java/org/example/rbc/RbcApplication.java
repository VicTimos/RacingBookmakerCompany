package org.example.rbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Main class preferably to place on the root package of the project to enable
// @ComponentScan for all subpackages
@SpringBootApplication
public class RbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(RbcApplication.class, args);
    }
}
