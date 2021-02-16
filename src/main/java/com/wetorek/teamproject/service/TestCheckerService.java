package com.wetorek.teamproject.service;

import com.wetorek.teamproject.entity.TestTemplate;
import com.wetorek.teamproject.repository.TestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TestCheckerService {
    private final TestRepository testRepository;

    public boolean existNotCheckedTest(final TestTemplate test) {
        return testRepository.existsByCheckedAndTestTemplate_Id(false, test.getId());
    }
}
