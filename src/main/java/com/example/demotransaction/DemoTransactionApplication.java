package com.example.demotransaction;

import com.example.demotransaction.application.case1.problem.Problem1OutService;
import com.example.demotransaction.application.case2.problem.Problem2OutService;
import com.example.demotransaction.application.case2.testcase1.TestCase1OutService;
import com.example.demotransaction.domain.Review;
import com.example.demotransaction.domain.ReviewRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@EnableJpaAuditing
@SpringBootApplication
public class DemoTransactionApplication {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    Problem1OutService outService;
//    Solution1OutService outService;
//    Solution2OutService outService;
//    Solution3OutService outService;
//    TestCase1OutService outService;
//    Problem2OutService outService;

    public static void main(String[] args) {
        SpringApplication.run(DemoTransactionApplication.class, args);
    }

    /*@PostConstruct
    public void init() throws Exception {
        outService.createReviewWithException();

        List<Review> all = reviewRepository.findAll();
        log.info("====================>>>>> 결과: 저장된 데이터 갯수 = {} 개", all.size());
    }*/
}
