package com.delicious.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.delicious.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
