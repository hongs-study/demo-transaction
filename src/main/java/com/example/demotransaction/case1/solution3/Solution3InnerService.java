package com.example.demotransaction.case1.solution3;

import com.example.demotransaction.Review;
import com.example.demotransaction.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
class Solution3InnerService {

    private final ReviewRepository reviewRepository;

    @org.springframework.transaction.annotation.Transactional(noRollbackFor = RuntimeException.class)
//    @javax.transaction.Transactional(dontRollbackOn = RuntimeException.class)
    public void createReviewWithException(Long userId, String text) {
        reviewRepository.save(Review.builder().userId(userId).text(text).build());

        throw new RuntimeException("에랏 받아랏 RuntimeException 예외다!");
    }
}


