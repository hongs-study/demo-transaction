package com.example.demotransaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
    public void uncheckedException(Long userId, String text) {
        reviewRepository.save(Review.builder().userId(userId).text(text).build());

        throw new RuntimeException("에랏 받아랏 RuntimeException 예외다!");
    }

    @Transactional
    public void checkedException(Long userId, String text) throws Exception {
        reviewRepository.save(Review.builder().userId(userId).text(text).build());

        throw new Exception("과연 이건 어떠냐?! Exception 예외다!");
    }


    /**
     * @Transactional 없을 때 커밋 되는지 확인
     */
    public void exceptionAndTransaction(Long userId, String text) {
        reviewRepository.save(Review.builder().userId(userId).text(text).build());

        throw new RuntimeException("에랏 받아랏 RuntimeException 예외다!");
    }
}


