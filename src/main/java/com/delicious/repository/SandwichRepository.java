package com.delicious.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.delicious.model.Sandwich;

public interface SandwichRepository extends JpaRepository<Sandwich, Long> {}