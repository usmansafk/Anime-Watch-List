package com.qa.awl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.awl.domain.AWL;

@Repository
public interface AWLRepo extends JpaRepository<AWL, Long> {

	AWL findByName(String name);

}

