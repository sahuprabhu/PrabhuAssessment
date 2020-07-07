package com.nordea.assessment.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nordea.assessment.entity.EndToEnd;

public interface EndToEndRepo extends JpaRepository<EndToEnd, Integer> {

}
