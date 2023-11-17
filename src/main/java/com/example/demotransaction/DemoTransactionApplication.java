package com.example.demotransaction;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.UnexpectedRollbackException;

@SpringBootApplication
public class DemoTransactionApplication {

    @Autowired
    DemoTransactionController controller;

    public static void main(String[] args) {
        SpringApplication.run(DemoTransactionApplication.class, args);
    }

    @PostConstruct
    public void init() {
        controller.go();
    }
}
