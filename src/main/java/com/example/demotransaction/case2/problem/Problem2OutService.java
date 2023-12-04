package com.example.demotransaction.case2.problem;

import com.example.demotransaction.Review;
import com.example.demotransaction.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class Problem2OutService {

    private final Problem2InnerService innerService;
    private final ReviewRepository reviewRepository;

    /**
     * Inner에 @Transactional 없을 때
     * 예상) OUT 로직 Rollback 된다
     * 결과) OUT 로직 Commit 됨!  =>  후처리에서 겪은 것과 동일한 현상. 호출되는 메서드에 트랜젝션이 없으면 RuntimeException이 발생해도 rollback 되지 않음!
     */
    @Transactional
    public void createReviewWithException() {
        log.info(" ========OUT 로직 시작========");

        // out 로직. 1번리뷰 저장
        reviewRepository.save(Review.builder().userId(1L).text("1번리뷰: 요래요래해서 좋았어요!!").build());

        try {
            // inner 로직 호출. 2번리뷰 저장
            innerService.createReviewWithException(2L, "2번리뷰: 요래요래해서 좋았어요!!");
        } catch (Exception ex) {
            // 의도: exception 받아서 로그만 찍기 때문에 이 메서드의 전체 로직은 정상작동(1,3번 리뷰 저장) 되어야 한다.
            log.error("리뷰 생성 중 에러 발생 - " + ex.getMessage());
        }

        // out 로직. 3번리뷰 저장
        reviewRepository.save(Review.builder().userId(3L).text("3번리뷰: 요래요래해서 좋았어요!!").build());

        log.info(" ========OUT 로직 완료========");
    }
}
