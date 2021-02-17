package com.wetorek.teamproject.service;

import com.wetorek.teamproject.dto.TestTemplateDtoRequest;
import com.wetorek.teamproject.entity.TestTemplate;
import com.wetorek.teamproject.exceptions.TemplateNotFound;
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
        if (!testTemplateRepository.existsById(id)) {
            var test = createTestTemplate(request);
            test.setId(id);
        }
        var test = testTemplateRepository.getOne(id);
        if (testCheckerService.existNotCheckedTest(test)) {
            throw new IllegalStateException("This test template has unchecked tests: " + id);
        }
        var updatedTest = updateTestTemplate(test, request);
        updatedTest.setId(test.getId());
        return testTemplateRepository.save(updatedTest);
    }

    public void delete(Integer id) {
        if (!testTemplateRepository.existsById(id)) {
            throw new TemplateNotFound();
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
