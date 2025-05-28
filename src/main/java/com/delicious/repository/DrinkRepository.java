package com.delicious.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.delicious.model.Drink;

public interface DrinkRepository extends JpaRepository<Drink, Long> {}