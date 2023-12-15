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
import org.springframework.transaction.UnexpectedRollbackException;

@DisplayName("Transaction 전파 케이스01 - 예외 처리했음에도 롤백되는 문제 해결하기")
@SpringBootTest
class Case01ControllerTest {

    @Autowired
    private Case01Controller controller;

    @Autowired
    private ReviewRepository reviewRepository;

    @DisplayName("문제재현] 예외 발생 후 try-catch 하여 처리했음에도 모두 롤백된다(의도는 커밋되어야 한다)")
    @Test
    void problem() {
        //when
        assertThrows(UnexpectedRollbackException.class, () -> controller.problem())
            .printStackTrace();

        //then
        List<Review> all = reviewRepository.findAll();
        assertThat(all).as("예외발생 & 모두 롤백").hasSize(0);
    }

    @DisplayName("해결방법01] Transaction 분리 => 예상결과: out 로직만 커밋")
    @Test
    void solution01() {
        //when
        List<Long> reviewIds = controller.solution01();

        //then
        List<Review> all = reviewRepository.findAll();
        assertThat(all).hasSize(reviewIds.size());
        assertThat(all.stream().map(Review::getId).toList()).isEqualTo(reviewIds);
    }

    @DisplayName("해결방법2] UncheckedException 대신 CheckedException을 발생 => 예상결과: 모든 로직 커밋")
    @Test
    void solution02() {
        List<Long> reviewIds = controller.solution02();

        List<Review> all = reviewRepository.findAll();
        assertThat(all).hasSize(3);
    }

    @DisplayName("해결방법3] Transactional의 noRollbackFor 속성 설정 => 예상결과: 모든 로직 커밋")
    @Test
    void solution03() {
        List<Long> reviewIds = controller.solution03();

        List<Review> all = reviewRepository.findAll();
        assertThat(all).hasSize(3);
    }
}
