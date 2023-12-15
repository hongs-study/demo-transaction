package com.example.demotransaction;

import com.example.demotransaction.application.case1.problem.Problem1OutService;
import com.example.demotransaction.application.case1.solution1.Solution1OutService;
import com.example.demotransaction.application.case1.solution2.Solution2OutService;
import com.example.demotransaction.application.case1.solution3.Solution3OutService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class Case01Controller {

    private final Problem1OutService service;
    private final Solution1OutService solution1OutService;
    private final Solution2OutService solution2OutService;
    private final Solution3OutService solution3OutService;

    public void problem() {
        service.createReviewWithException();
    }

    public List<Long> solution01() {
        return solution1OutService.createReviewWithException();
    }

    public List<Long> solution02() {
        return solution2OutService.createReviewWithException();
    }

    public List<Long> solution03() {
        return solution3OutService.createReviewWithException();
    }
}
