package com.example.demotransaction.application.case2.testcase1;

import com.example.demotransaction.domain.Review;
import com.example.demotransaction.domain.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
class TestCase1InnerService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public void createReviewWithException(Long userId, String text) throws Exception {
        reviewRepository.save(Review.builder().userId(userId).text(text).build());

        throw new Exception("에랏 받아랏 Exception 예외다!");
    }
}


