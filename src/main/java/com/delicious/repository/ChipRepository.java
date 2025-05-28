package com.delicious.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.delicious.model.Chip;

public interface ChipRepository extends JpaRepository<Chip, Long> {}
