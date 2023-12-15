package com.example.demotransaction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.demotransaction.domain.Review;
import com.example.demotransaction.domain.ReviewRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 전제조건(예상)
 * CheckedException : 커밋
 * UncheckedException : 롤백
 */
@DisplayName("Transaction 전파 케이스02 - 롤백을 예상했으나 모든 로직 커밋 되버림")
@SpringBootTest
class Case02ControllerTest {

    @Autowired
    private Case02Controller controller;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void problem() {
        List<Long> reviewIds = controller.problem();

        List<Review> all = reviewRepository.findAll();
        System.out.println("all.size() = " + all.size());

        assertThat(all).hasSize(0);
    }
}
