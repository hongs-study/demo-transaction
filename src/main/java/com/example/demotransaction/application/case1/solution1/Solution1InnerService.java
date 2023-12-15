package com.example.demotransaction.application.case1.solution1;

import com.example.demotransaction.domain.Review;
import com.example.demotransaction.domain.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
class Solution1InnerService {

    private final ReviewRepository reviewRepository;

    // 해결방법1: Transaction을 분리 한다. 방법은 REQUIRES_NEW 지정.
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createReviewWithException(Long userId, String text) {
        reviewRepository.save(Review.builder().userId(userId).text(text).build());

        throw new RuntimeException("에랏 받아랏 RuntimeException 예외다!");
    }
}


