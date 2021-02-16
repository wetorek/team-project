package com.wetorek.teamproject.controller;

import com.wetorek.teamproject.dto.TestDtoRequest;
import com.wetorek.teamproject.dto.TestDtoResponse;
import com.wetorek.teamproject.mapper.TestMapper;
import com.wetorek.teamproject.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tests")
public class TestController {
    private final TestService testService;
    private final TestMapper testMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TestDtoResponse createTestForUser(@RequestParam int userId, @RequestParam int templateId) {
        var test = testService.createTest(userId, templateId);
        return testMapper.mapToResponse(test);
    }

    @GetMapping(params = "templateId")
    List<TestDtoResponse> getTestsByTemplate(@RequestParam("templateId") int templateId) {
        var tests = testService.getAllTestsByTemplate(templateId);
        return tests.stream()
                .map(testMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @GetMapping(params = "userId")
    List<TestDtoResponse> getTestsByUser(@RequestParam("userId") int userId) {
        var tests = testService.getAllTestsByUser(userId);
        return tests.stream()
                .map(testMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    TestDtoResponse submitTest(@PathVariable int id, @RequestBody TestDtoRequest testDtoRequest) {
        var test = testService.submitTest(id, testDtoRequest);
        return testMapper.mapToResponse(test);
    }
}
