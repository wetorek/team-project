package com.wetorek.teamproject.service;

import com.wetorek.teamproject.dto.TestTemplateDtoRequest;
import com.wetorek.teamproject.entity.TestTemplate;
import com.wetorek.teamproject.mapper.TestTemplateMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TestTemplateFactory {
    private final TestTemplateMapper templateMapper;
    private final UserService userService;

    public TestTemplate from(final TestTemplateDtoRequest request) {
        var testTemplate = templateMapper.mapRequestToEntity(request);
        var user = userService.getCurrentUser()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        testTemplate.setUser(user);
        return testTemplate;
    }
}
