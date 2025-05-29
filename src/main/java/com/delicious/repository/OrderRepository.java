package com.delicious.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.delicious.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
