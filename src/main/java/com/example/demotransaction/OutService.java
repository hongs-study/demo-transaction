package com.example.demotransaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class OutService {

    private final InnerService innerService;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void createReviewWithException() {
        // out 로직. 1번리뷰 저장
        saveReview(1L);

        try {
            // inner 로직 호출. 2번리뷰 저장
            innerService.exceptionAndTransaction(2L, "2번리뷰: 요래요래해서 좋았어요!! 또 방문할거에요!");
        } catch (Exception ex) {
            // 의도: exception 받아서 로그만 찍기 때문에 이 메서드의 전체 로직은 정상작동(1,3번 리뷰 저장) 되어야 한다.
            log.error("리뷰 생성 중 에러 발생 - " + ex.getMessage());
        }

        // out 로직. 3번리뷰 저장
        saveReview(3L);

        updateReview();

        log.info("완료");
    }

    private void saveReview(long userId) {
        log.info(">>> 리뷰 저장 고고고 : userId={}", userId);
        reviewRepository.save(Review.builder().userId(userId).text("%d번리뷰: 요래요래해서 좋았어요!! 또 방문할거에요!".formatted(userId)).build());
    }

    private void updateReview() {
        Review review = reviewRepository.findById(1L).get();
        review.setText("바꾼다!!");
    }

    /**
     * case) 만약 checkedException을 발생시키지만, 예외처리를 별도로 하지 않으면? 롤백 vs 커밋?
     * result) OUT/IN 모두 커밋. 단, Throw 이후의 로직은 실행되지 않는다.
     */
    @Transactional
    public void caseCheckedException() throws Exception {
        // out 로직. 1번리뷰 저장
        saveReview(1L);

        // 예외처리 따로 catch하지 않고 throw해버린다.
        // inner 로직 호출. 2번리뷰 저장
        innerService.checkedException(2L, "2번리뷰: 요래요래해서 좋았어요!! 또 방문할거에요!");

        // out 로직. 3번리뷰 저장 => 위 innerService에서 Exception 발생해서 여기부터 실행되지 않는다
        saveReview(3L);

        updateReview();

        log.info("완료");
    }
}
