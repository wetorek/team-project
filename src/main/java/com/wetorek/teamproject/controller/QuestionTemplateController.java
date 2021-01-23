package com.wetorek.teamproject.controller;

import com.wetorek.teamproject.dto.QuestionTemplateDtoRequest;
import com.wetorek.teamproject.dto.QuestionTemplateDtoResponse;
import com.wetorek.teamproject.exceptions.QuestionTemplateNotFound;
import com.wetorek.teamproject.mapper.QuestionTemplateMapper;
import com.wetorek.teamproject.service.QuestionTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/question-templates")
class QuestionTemplateController {
    private final QuestionTemplateMapper questionTemplateMapper;
    private final QuestionTemplateService questionTemplateService;


    @GetMapping("/{questionId}")
    ResponseEntity<QuestionTemplateDtoResponse> getQuestionTemplateById(@PathVariable Integer questionId) {
        var questionTemplate = questionTemplateService.getQuestionTemplateById(questionId);
        return questionTemplate
                .map(questionTemplateMapper::mapEntityToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    List<QuestionTemplateDtoResponse> getQuestionTemplatesByTestId(@RequestParam Integer testId) {
        var questionTemplates = questionTemplateService.getQuestionTemplatesByTestId(testId);
        return questionTemplateMapper.mapListOfEntityToResponses(questionTemplates);
    }

    /*
     * Will be used rarely- we mostly would post entire test templates
     * */
    @PostMapping
    QuestionTemplateDtoResponse postQuestionTemplate(@RequestParam Integer testId, @RequestBody QuestionTemplateDtoRequest request) {
        var questionTemplate = questionTemplateService.createNewQuestionTemplate(testId, request);
        return questionTemplateMapper.mapEntityToResponse(questionTemplate);
    }

    @PutMapping("/{questionId}")
    QuestionTemplateDtoResponse replaceQuestionTemplate(@PathVariable Integer questionId, @RequestParam Integer newTestId, @RequestBody QuestionTemplateDtoRequest request) {
        var questionTemplate = questionTemplateService.updateQuestionTemplate(questionId, newTestId, request);
        return questionTemplateMapper.mapEntityToResponse(questionTemplate);
    }

    @DeleteMapping("/{questionId}")
    void deleteQuestionTemplate(@PathVariable Integer questionId) {
        questionTemplateService.deleteQuestionTemplate(questionId);
    }

    //todo implement iterator pattern here
    public QuestionTemplateDtoResponse getNextQuestion() {
        return null;
    }

    @ExceptionHandler(IllegalStateException.class)
    ResponseEntity<String> handleClientError(IllegalStateException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuestionTemplateNotFound.class)
    ResponseEntity<String> handleClientError(QuestionTemplateNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
