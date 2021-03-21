package com.wetorek.teamproject.controller;

import com.wetorek.teamproject.dto.TestDtoRequest;
import com.wetorek.teamproject.dto.TestDtoResponse;
import com.wetorek.teamproject.mapper.TestMapper;
import com.wetorek.teamproject.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tests")
public class TestController {
    private final TestService testService;
    private final TestMapper testMapper;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<TestDtoResponse> createTestForUser(@RequestParam int userId, @RequestParam int templateId) {
        var test = testService.createTest(userId, templateId);
        var testDto = testMapper.mapToDto(test);
        return ResponseEntity.created(URI.create("/" + testDto.getId())).body(testDto);
    }

    @GetMapping("/{id}")
    @PostAuthorize("(returnObject.statusCode.value() == 404) OR (returnObject.body.examinedUserId == authentication.principal.userId) OR hasAuthority('ROLE_ADMIN')")
    ResponseEntity<TestDtoResponse> getTestById(@PathVariable int id) {
        var test = testService.getTestById(id);
        return test.map(testMapper::mapToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(params = "templateId")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    List<TestDtoResponse> getTestsByTemplate(@RequestParam("templateId") int templateId) {
        var tests = testService.getAllTestsByTemplate(templateId);
        return tests.stream()
                .map(testMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(params = "userId")
    @PreAuthorize("#userId == authentication.principal.userId OR hasAuthority('ROLE_ADMIN')")
    List<TestDtoResponse> getTestsByUser(@RequestParam("userId") Integer userId) {
        var tests = testService.getAllTestsByUser(userId);
        return tests.stream()
                .map(testMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @PreAuthorize("#testDtoRequest.userId == authentication.principal.userId OR hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void submitTest(@PathVariable int id, @RequestBody @Valid TestDtoRequest testDtoRequest) {
        testService.submitTest(id, testDtoRequest);
    }
}
