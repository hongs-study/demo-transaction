package com.example.demotransaction.application.case2.problem;

import com.example.demotransaction.domain.Review;
import com.example.demotransaction.domain.ReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@RequiredArgsConstructor
@Service
public class Problem2OutService {

    private final Problem2InnerService innerService;
    private final ReviewRepository reviewRepository;

    /**
     * <pre>
     *     Inner에 @Transactional 없을 때
     *     예상) OUT 로직 Rollback 된다
     *     결과) OUT 로직 Commit 됨! => 후처리에서 겪은 것과 동일한 현상. 호출되는 메서드에 트랜젝션이 없으면 RuntimeException이 발생해도 rollback 되지 않음!
     * </pre>
     */
    @Transactional
    public List<Long> createReviewWithException() {
        log.info(" ========OUT 로직 시작========");

        System.out.println("isActualTransactionActive() = " + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("isSynchronizationActive() = " + TransactionSynchronizationManager.isSynchronizationActive());
        System.out.println("getCurrentTransactionName() = " + TransactionSynchronizationManager.getCurrentTransactionName());


        // out 로직. 1번리뷰 저장
        Review review01 = reviewRepository.save(Review.builder().userId(1L).text("1번리뷰: 요래요래해서 좋았어요!!").build());

        try {
            // inner 로직 호출. 2번리뷰 저장
            innerService.createReviewWithException(2L, "2번리뷰: 요래요래해서 좋았어요!!");
        } catch (Exception ex) {
            // 의도: exception 을 처리했기 때문에 적어도(inner는 몰라도) out의 로직들은 커밋되어야 한다.
            log.error("리뷰 생성 중 에러 발생 - " + ex.getMessage());
        }

        // out 로직. 3번리뷰 저장
        Review review02 = reviewRepository.save(Review.builder().userId(3L).text("3번리뷰: 요래요래해서 좋았어요!!").build());

        log.info(" ========OUT 로직 완료========");
        return List.of(review01.getId(), review02.getId());
    }
}
