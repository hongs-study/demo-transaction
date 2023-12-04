package com.example.demotransaction.case2.problem;

import com.example.demotransaction.Review;
import com.example.demotransaction.ReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
class Problem2InnerService {

    private final ReviewRepository reviewRepository;


    // @Transactional 어노테이션 제거 테스트
    public void createReviewWithException(Long userId, String text) {
        //reviewRepository.save(Review.builder().userId(userId).text(text).build());

        List<Review> all = reviewRepository.findAll();
        log.info("  >>>>>> 여기는 INNER! size={}", all.size());

        throw new RuntimeException("에랏 받아랏 RuntimeException 예외다!");
    }
}
