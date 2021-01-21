package com.wetorek.teamproject.repository;

import com.wetorek.teamproject.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Integer> {
    boolean existsByCheckedAndTestTemplate_Id(Boolean checked, Integer testTemplateId);
}
