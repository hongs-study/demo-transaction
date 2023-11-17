package com.example.demotransaction;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDatabaseConnectTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    void name() {
        Review review = Review.builder()
            .userId(1L)
            .text("좋았어요!")
            .build();

        Review save = reviewRepository.save(review);
        Optional<Review> find = reviewRepository.findById(save.getId());

        System.out.println("find.get().getText() = " + find.get().getText());

    }
}
