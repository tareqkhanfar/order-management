package com.khanfar.project2.Repository;

import com.khanfar.project2.DTO.ProductOrderId;
import com.khanfar.project2.Entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderId> {
    // Additional custom query methods...
}
