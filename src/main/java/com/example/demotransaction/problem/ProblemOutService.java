package com.example.demotransaction.problem;

import com.example.demotransaction.Review;
import com.example.demotransaction.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Out 클래스. 문제 상황 재현
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProblemOutService {

    private final ProblemInnerService problemInnerService;
    private final ReviewRepository reviewRepository;

    /**
     * 결과: 에러발생. UnexpectedRollbackException: Transaction silently rolled back because it has been marked as rollback-only
     */
    @Transactional
    public void createReviewWithException() {
        log.info(" ========OUT 로직 시작========");

        // out 로직. 1번리뷰 저장
        reviewRepository.save(Review.builder().userId(1L).text("1번리뷰: 요래요래해서 좋았어요!!").build());

        try {
            // inner 로직 호출. 2번리뷰 저장
            problemInnerService.createReviewWithException(2L, "2번리뷰: 요래요래해서 좋았어요!!");
        } catch (Exception ex) {
            // 의도: exception 받아서 로그만 찍기 때문에 이 메서드의 전체 로직은 정상작동(1,3번 리뷰 저장) 되어야 한다.
            log.error("리뷰 생성 중 에러 발생 - " + ex.getMessage());
        }

        // out 로직. 3번리뷰 저장
        reviewRepository.save(Review.builder().userId(3L).text("3번리뷰: 요래요래해서 좋았어요!!").build());

        log.info(" ========OUT 로직 완료========");
    }
}
