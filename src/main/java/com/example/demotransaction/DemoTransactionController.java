package com.example.demotransaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;

@RequiredArgsConstructor
@Controller
public class DemoTransactionController {

    private final OutService outService;
    private final ReviewRepository reviewRepository;

    public void go() {
        reviewRepository.save(Review.builder().userId(0L).text("%d번리뷰: 요래요래해서 좋았어요!! 또 방문할거에요!".formatted(0L)).build());

        try {
            //outService.createReviewWithException();
            outService.caseCheckedException();
        } catch (UnexpectedRollbackException e) {
            System.out.println(">>>>>>>>>>>>>>> 에러발생에러발생 UnexpectedRollbackException !!!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>>>> 에러발생에러발생 Exception !!!");
          throw new RuntimeException(e);
        }
    }
}
