package com.example.demotransaction;

import com.example.demotransaction.application.case2.problem.Problem2OutService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class Case02Controller {

    private final Problem2OutService problem2OutService;

    public List<Long> problem() {
        return problem2OutService.createReviewWithException();
    }

}
