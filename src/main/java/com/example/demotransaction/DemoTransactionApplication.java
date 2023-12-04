package com.example.demotransaction;

import com.example.demotransaction.testcase1.TestCase1OutService;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DemoTransactionApplication {

    @Autowired
    DemoTransactionController controller;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
//    ProblemOutService outService;
//    Solution1OutService outService;
//    Solution2OutService outService;
//    Solution3OutService outService;
    TestCase1OutService outService;

    public static void main(String[] args) {
        SpringApplication.run(DemoTransactionApplication.class, args);
    }

    @PostConstruct
    public void init() throws Exception {
        outService.createReviewWithException();

        List<Review> all = reviewRepository.findAll();
        log.info("====================>>>>> 결과: 저장된 데이터 갯수 = {} 개", all.size());
    }
}
