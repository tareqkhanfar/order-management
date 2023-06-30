package com.khanfar.project2.Repository;

import com.khanfar.project2.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Additional custom query methods...
}
