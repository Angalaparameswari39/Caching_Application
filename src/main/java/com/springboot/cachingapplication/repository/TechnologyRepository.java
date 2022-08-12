package com.springboot.cachingapplication.repository;

import com.springboot.cachingapplication.domain.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnologyRepository extends JpaRepository<Technology, UUID> {
}
