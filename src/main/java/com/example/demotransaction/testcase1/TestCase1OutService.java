package com.example.demotransaction.testcase1;

import com.example.demotransaction.Review;
import com.example.demotransaction.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestCase1OutService {

    private final TestCase1InnerService innerService;
    private final ReviewRepository reviewRepository;

    /**
     * case1) 만약 checkedException을 발생시키지만, 예외처리를 별도로 하지 않으면? 롤백 vs 커밋?
     * result) OUT/IN 모두 커밋. 단, Throw 이후의 로직은 실행되지 않는다.
     *      주의: 그래서 주의해야한다... Inner 에서도 throw 이전 저장은 다 되기 떄문에.. 로직의 일부는 커밋되고, 일부는 아예 실행조차되지 않기 때문..
     */
    @Transactional
    public void createReviewWithException() throws Exception {
        log.info(" ========OUT 로직 시작========");

        // out 로직. 1번리뷰 저장
        reviewRepository.save(Review.builder().userId(1L).text("1번리뷰: 요래요래해서 좋았어요!!").build());

        // inner 로직 호출. 2번리뷰 저장 => 예외처리 따로 catch하지 않고 throw해버린다.
        innerService.createReviewWithException(2L, "2번리뷰: 요래요래해서 좋았어요!!");
        // 이 하위 로직은 throw Exception 으로 실행되지 않는다.

        // out 로직. 3번리뷰 저장
        reviewRepository.save(Review.builder().userId(3L).text("3번리뷰: 요래요래해서 좋았어요!!").build());

        log.info(" ========OUT 로직 완료========");
    }
}
