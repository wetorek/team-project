package com.wetorek.teamproject.controller;

import com.wetorek.teamproject.dto.TestTemplateDtoRequest;
import com.wetorek.teamproject.dto.TestTemplateDtoResponse;
import com.wetorek.teamproject.mapper.TestTemplateMapper;
import com.wetorek.teamproject.service.TestTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/test-templates")
class TestTemplateController {
    private final TestTemplateService testTemplateService;
    private final TestTemplateMapper testTemplateMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TestTemplateDtoResponse> getAll() {
        var testTemplates = testTemplateService.getAll();
        return testTemplateMapper.mapListOfEntitiesToResponses(testTemplates);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<TestTemplateDtoResponse> create(@RequestBody @Valid TestTemplateDtoRequest request) {
        var createdTest = testTemplateService.createTestTemplate(request);
        return ResponseEntity.created(URI.create("/" + createdTest.getId())).body(testTemplateMapper.mapEntityToResponse(createdTest));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<TestTemplateDtoResponse> getById(@PathVariable Integer id) {
        var testTemplate = testTemplateService.getById(id);
        return testTemplate
                .map(testTemplateMapper::mapEntityToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    TestTemplateDtoResponse update(@PathVariable Integer id, @RequestBody @Valid TestTemplateDtoRequest request) {
        var testTemplate = testTemplateService.updateOrCreate(id, request);
        return testTemplateMapper.mapEntityToResponse(testTemplate);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        testTemplateService.delete(id);
    }
}
