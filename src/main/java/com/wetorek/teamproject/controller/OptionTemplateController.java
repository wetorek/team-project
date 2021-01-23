package com.wetorek.teamproject.controller;

import com.wetorek.teamproject.dto.OptionTemplateDtoRequest;
import com.wetorek.teamproject.dto.OptionTemplateDtoResponse;
import com.wetorek.teamproject.exceptions.QuestionTemplateNotFound;
import com.wetorek.teamproject.mapper.OptionTemplateMapper;
import com.wetorek.teamproject.service.OptionTemplateService;
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
@RequestMapping("/api/option-templates")
class OptionTemplateController {

    private final OptionTemplateService optionTemplateService;
    private final OptionTemplateMapper optionTemplateMapper;


    @GetMapping("/{optionId}")
    ResponseEntity<OptionTemplateDtoResponse> getOptionTemplateById(@PathVariable Integer optionId) {
        var optionTemplate = optionTemplateService.getOptionTemplateById(optionId);
        return optionTemplate
                .map(optionTemplateMapper::mapEntityToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    List<OptionTemplateDtoResponse> getOptionTemplatesByQuestionId(@RequestParam Integer questionId) {
        var optionTemplates = optionTemplateService.getOptionTemplatesByQuestionId(questionId);
        return optionTemplateMapper.mapListOfEntityToDto(optionTemplates);
    }

    /*
     * Will be used rarely- we mostly would post entire test templates
     * */
    @PostMapping
    OptionTemplateDtoResponse postOptionTemplate(@RequestParam Integer questionId, @RequestBody OptionTemplateDtoRequest request) {
        var optionTemplate = optionTemplateService.createNewOptionTemplate(questionId, request);
        return optionTemplateMapper.mapEntityToResponse(optionTemplate);
    }

    @PutMapping("/{optionId}")
    OptionTemplateDtoResponse replaceQuestionTemplate(@PathVariable Integer optionId, @RequestParam Integer newQuestionId, @RequestBody OptionTemplateDtoRequest request) {
        var optionTemplate = optionTemplateService.updateOptionTemplate(optionId, newQuestionId, request);
        return optionTemplateMapper.mapEntityToResponse(optionTemplate);
    }

    @DeleteMapping("/{optionId}")
    void deleteQuestionTemplate(@PathVariable Integer optionId) {
        optionTemplateService.deleteOptionTemplate(optionId);
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
