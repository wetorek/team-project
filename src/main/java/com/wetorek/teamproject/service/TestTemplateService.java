package com.wetorek.teamproject.service;

import com.wetorek.teamproject.dto.TestTemplateDtoRequest;
import com.wetorek.teamproject.entity.TestTemplate;
import com.wetorek.teamproject.exceptions.TemplateNotFoundEx;
import com.wetorek.teamproject.repository.TestTemplateRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TestTemplateService {
    private final TestTemplateRepository testTemplateRepository;
    private final TestTemplateFactory testTemplateFactory;
    private final TestCheckerService testCheckerService;

    public TestTemplate createTestTemplate(final TestTemplateDtoRequest testTemplateDtoRequest) {
        var test = testTemplateFactory.from(testTemplateDtoRequest);
        var savedTest = testTemplateRepository.save(test);
        log.info("TestTemplate was created: " + savedTest.getId());
        return savedTest;
    }

    public List<TestTemplate> getAll() {
        return testTemplateRepository.findAll();
    }

    public Optional<TestTemplate> getById(Integer id) {
        return testTemplateRepository.findById(id);
    }

    public TestTemplate updateOrCreate(Integer id, final TestTemplateDtoRequest request) {
        var template = testTemplateRepository.findById(id)
                .map(testTemplate -> {
                    if (testCheckerService.existNotCheckedTest(testTemplate)) {
                        throw new IllegalStateException("This test template has unchecked tests: " + id);
                    }
                    return updateTestTemplate(testTemplate, request);
                })
                .orElseGet(() -> {
                    var test = createTestTemplate(request);
                    test.setId(id);
                    return test;
                });
        template.setId(id);
        return testTemplateRepository.save(template);
    }

    public void delete(Integer id) {
        if (!testTemplateRepository.existsById(id)) {
            throw new TemplateNotFoundEx("Test template not exists");
        }
        testTemplateRepository.deleteById(id);
    }

    private TestTemplate updateTestTemplate(TestTemplate testTemplate, final TestTemplateDtoRequest request) {
        var newTest = testTemplateFactory.from(request);
        testTemplate.setDescription(request.getDescription());
        testTemplate.setTitle(request.getTitle());
        testTemplate.clearAndAddAll(newTest.getQuestionTemplates());
        testTemplate.getQuestionTemplates().forEach(questionTemplate -> questionTemplate.attachParentEntities(testTemplate));
        return testTemplate;
    }
}
