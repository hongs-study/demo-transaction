package com.example.demotransaction.application.case2.problem;

import com.example.demotransaction.domain.Review;
import com.example.demotransaction.domain.ReviewRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@RequiredArgsConstructor
@Service
class Problem2InnerService {

    private final ReviewRepository reviewRepository;

    // @Transactional 어노테이션 제거 테스트
    public void createReviewWithException(Long userId, String text) {
        System.out.println("========== IN ==========================");
        System.out.println("isActualTransactionActive() = " + TransactionSynchronizationManager.isActualTransactionActive());
        System.out.println("isSynchronizationActive() = " + TransactionSynchronizationManager.isSynchronizationActive());
        System.out.println("getCurrentTransactionName() = " + TransactionSynchronizationManager.getCurrentTransactionName());
        Map<Object, Object> resourceMap = TransactionSynchronizationManager.getResourceMap();

        reviewRepository.save(Review.builder().userId(userId).text(text).build());

        throw new RuntimeException("에랏 받아랏 RuntimeException 예외다!");
    }
}
