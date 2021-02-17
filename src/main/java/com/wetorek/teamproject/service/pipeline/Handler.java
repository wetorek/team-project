package com.wetorek.teamproject.service.pipeline;

import com.wetorek.teamproject.entity.Test;

interface Handler {
    void process(Test test);
}
