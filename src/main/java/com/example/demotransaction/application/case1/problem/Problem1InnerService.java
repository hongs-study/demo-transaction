package com.example.demotransaction.application.case1.problem;

import com.example.demotransaction.domain.Review;
import com.example.demotransaction.domain.ReviewRepository;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

// Inner 클래스
@RequiredArgsConstructor
@Service
class Problem1InnerService {

    private final ReviewRepository reviewRepository;

    // 저장 + 예외
    @Transactional
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
