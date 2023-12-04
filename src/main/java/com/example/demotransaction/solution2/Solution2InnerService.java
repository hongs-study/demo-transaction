package com.example.demotransaction.solution2;

import com.example.demotransaction.Review;
import com.example.demotransaction.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
class Solution2InnerService {

    private final ReviewRepository reviewRepository;

    // 해결방법 CheckedException로 발생시키기
    @Transactional
    public void createReviewWithException(Long userId, String text) throws Exception {
        reviewRepository.save(Review.builder().userId(userId).text(text).build());

        //throw new RuntimeException("에랏 받아랏 RuntimeException 예외다!");
        throw new Exception("에랏 받아랏 Exception 예외다!");
    }
}
