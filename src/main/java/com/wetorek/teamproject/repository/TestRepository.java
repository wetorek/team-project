package com.wetorek.teamproject.repository;

import com.wetorek.teamproject.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Integer> {
    boolean existsByCheckedAndTestTemplate_Id(Boolean checked, Integer testTemplateId);

    List<Test> findTestByTestTemplate_Id(int testTemplateId);

    List<Test> findTestByExaminedUser_Id(int userId);
}
