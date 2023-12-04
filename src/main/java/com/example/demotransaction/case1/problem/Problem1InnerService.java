package com.example.demotransaction.case1.problem;

import com.example.demotransaction.Review;
import com.example.demotransaction.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Inner 클래스
@RequiredArgsConstructor
@Service
class Problem1InnerService {

    private final ReviewRepository reviewRepository;

    // 저장 + 예외
    @Transactional
    public void createReviewWithException(Long userId, String text) {
        reviewRepository.save(Review.builder().userId(userId).text(text).build());

        throw new RuntimeException("에랏 받아랏 RuntimeException 예외다!");
    }
}
