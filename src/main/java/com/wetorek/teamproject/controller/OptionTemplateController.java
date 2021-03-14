package com.wetorek.teamproject.controller;

import com.wetorek.teamproject.dto.OptionTemplateDtoRequest;
import com.wetorek.teamproject.dto.OptionTemplateDtoResponse;
import com.wetorek.teamproject.mapper.OptionTemplateMapper;
import com.wetorek.teamproject.service.OptionTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/option-templates")
class OptionTemplateController {
    private final OptionTemplateService optionTemplateService;
    private final OptionTemplateMapper mapper;

    @GetMapping("/{optionId}")
    ResponseEntity<OptionTemplateDtoResponse> getOptionTemplateById(@PathVariable Integer optionId) {
        var optionTemplate = optionTemplateService.getOptionTemplateById(optionId);
        return optionTemplate
                .map(mapper::mapToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    List<OptionTemplateDtoResponse> getOptionTemplatesByQuestionId(@RequestParam Integer questionId) {
        var optionTemplates = optionTemplateService.getOptionTemplatesByQuestionId(questionId);
        return mapper.mapCollectionToDto(optionTemplates);
    }

    /*
     * Will be used rarely- we mostly would post entire test templates
     * */
    @PostMapping
    OptionTemplateDtoResponse postOptionTemplate(@RequestParam Integer questionId, @RequestBody @Valid OptionTemplateDtoRequest request) {
        var optionTemplate = optionTemplateService.createNewOptionTemplate(questionId, request);
        return mapper.mapToDto(optionTemplate);
    }

    @PutMapping("/{optionId}")
    OptionTemplateDtoResponse replaceQuestionTemplate(@PathVariable Integer optionId, @RequestParam Integer newQuestionId, @RequestBody @Valid OptionTemplateDtoRequest request) {
        var optionTemplate = optionTemplateService.updateOptionTemplate(optionId, newQuestionId, request);
        return mapper.mapToDto(optionTemplate);
    }

    @DeleteMapping("/{optionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteQuestionTemplate(@PathVariable Integer optionId) {
        optionTemplateService.deleteOptionTemplate(optionId);
    }

}
