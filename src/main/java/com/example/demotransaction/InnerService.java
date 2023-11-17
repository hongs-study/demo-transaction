package com.example.demotransaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class InnerService {

    private final ReviewRepository reviewRepository;

    //    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional
//    @org.springframework.transaction.annotation.Transactional(noRollbackFor = RuntimeException.class)
//    @javax.transaction.Transactional(dontRollbackOn = RuntimeException.class)
    public void createReviewWithException(Long userId, String text) {
        reviewRepository.save(Review.builder().userId(userId).text(text).build());

        throw new RuntimeException("에랏 받아랏 RuntimeException 예외다!");
    }
}


