package com.wetorek.teamproject.controller;

import com.wetorek.teamproject.dto.QuestionTemplateDtoRequest;
import com.wetorek.teamproject.dto.QuestionTemplateDtoResponse;
import com.wetorek.teamproject.mapper.QuestionTemplateMapper;
import com.wetorek.teamproject.service.QuestionTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/question-templates")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
class QuestionTemplateController {
    private final QuestionTemplateMapper mapper;
    private final QuestionTemplateService questionTemplateService;

    @GetMapping("/{questionId}")
    ResponseEntity<QuestionTemplateDtoResponse> getQuestionTemplateById(@PathVariable Integer questionId) {
        var questionTemplate = questionTemplateService.getQuestionTemplateById(questionId);
        return questionTemplate
                .map(mapper::mapToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    List<QuestionTemplateDtoResponse> getQuestionTemplatesByTestId(@RequestParam Integer testId) {
        var questionTemplates = questionTemplateService.getQuestionTemplatesByTestId(testId);
        return mapper.mapCollectionToDto(questionTemplates);
    }

    /*
     * Will be used rarely- we mostly would post entire test templates
     * */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    QuestionTemplateDtoResponse postQuestionTemplate(@RequestParam Integer testId, @RequestBody @Valid QuestionTemplateDtoRequest request) {
        var questionTemplate = questionTemplateService.createNewQuestionTemplate(testId, request);
        return mapper.mapToDto(questionTemplate);
    }

    @PutMapping("/{questionId}")
    QuestionTemplateDtoResponse replaceQuestionTemplate(@PathVariable Integer questionId, @RequestParam Integer newTestId, @RequestBody @Valid QuestionTemplateDtoRequest request) {
        var questionTemplate = questionTemplateService.updateQuestionTemplate(questionId, newTestId, request);
        return mapper.mapToDto(questionTemplate);
    }

    @DeleteMapping("/{questionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteQuestionTemplate(@PathVariable Integer questionId) {
        questionTemplateService.deleteQuestionTemplate(questionId);
    }

    //todo implement iterator pattern here
    public QuestionTemplateDtoResponse getNextQuestion() {
        return null;
    }
}
