package com.nordea.assessment.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nordea.assessment.entity.DocumentErrorLog;


public interface DocumentErrorRepo extends JpaRepository<DocumentErrorLog, Integer> {

}
