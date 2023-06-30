package com.khanfar.project2.Repositories;

import com.khanfar.project2.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product , Integer> {
}
