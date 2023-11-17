package com.example.demotransaction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoTransactionControllerTest {

    @Autowired
    DemoTransactionController controller;
    @Autowired
    ReviewRepository reviewRepository;

    @Test
    void name() {
        controller.go();

        List<Review> all = reviewRepository.findAll();
        System.out.println(">>> all.size() = " + all.size());
    }
}
