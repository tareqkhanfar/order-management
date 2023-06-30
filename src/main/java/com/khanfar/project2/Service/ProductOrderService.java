package com.khanfar.project2.Service;

import com.khanfar.project2.DTO.ProductOrderId;
import com.khanfar.project2.Entity.ProductOrder;
import com.khanfar.project2.Repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderService {
    @Autowired

    private  ProductOrderRepository productOrderRepository;



    public List<ProductOrder> getAllProductOrders() {
        return productOrderRepository.findAll();
    }

    public Optional<ProductOrder> getProductOrderById(ProductOrderId id) {
        return productOrderRepository.findById(id);
    }

    public ProductOrder saveProductOrder(ProductOrder productOrder) {
        return productOrderRepository.save(productOrder);
    }

    public void deleteProductOrder(ProductOrderId id) {
        productOrderRepository.deleteById(id);
    }
}
