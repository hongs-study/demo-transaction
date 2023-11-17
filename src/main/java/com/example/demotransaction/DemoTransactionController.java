package com.example.demotransaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;

@RequiredArgsConstructor
@Controller
public class DemoTransactionController {

    private final OutService outService;

    public void go() {
        try {
            outService.createReviewWithException();
        } catch (UnexpectedRollbackException e) {
            System.out.println("에러발생에러발생!!!!!!!!1");
            e.printStackTrace();
        }
    }
}
