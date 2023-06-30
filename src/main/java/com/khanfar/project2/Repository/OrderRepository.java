package com.khanfar.project2.Repository;

import com.khanfar.project2.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
