package com.khanfar.project2.Service;

import com.khanfar.project2.DTO.ProductOrderDTO;
import com.khanfar.project2.DTO.ProductOrderId;
import com.khanfar.project2.Entity.ProductOrder;
import com.khanfar.project2.Repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductOrderService {
    @Autowired
    private ProductOrderRepository productOrderRepository;

    public List<ProductOrderDTO> getAllProductOrders() {
        List<ProductOrder> productOrders = productOrderRepository.findAll();
        return productOrders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductOrderDTO> getProductOrderById(ProductOrderId id) {
        Optional<ProductOrder> productOrder = productOrderRepository.findById(id);
        return productOrder.map(this::convertToDTO);
    }

    public ProductOrderDTO saveProductOrder(ProductOrderDTO productOrderDTO) {
        ProductOrder productOrder = convertToEntity(productOrderDTO);
        ProductOrder savedProductOrder = productOrderRepository.save(productOrder);
        return convertToDTO(savedProductOrder);
    }

    public void deleteProductOrder(ProductOrderId id) {
        productOrderRepository.deleteById(id);
    }

    private ProductOrderDTO convertToDTO(ProductOrder productOrder) {
        ProductOrderDTO productOrderDTO = new ProductOrderDTO();
        productOrderDTO.setProductId(productOrder.getProductId());
        productOrderDTO.setOrderId(productOrder.getOrderId());
        productOrderDTO.setQuantity(productOrder.getQuantity());
        // Set other properties as needed
        return productOrderDTO;
    }

    private ProductOrder convertToEntity(ProductOrderDTO productOrderDTO) {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setProductId(productOrderDTO.getProductId());
        productOrder.setOrderId(productOrderDTO.getOrderId());
        productOrder.setQuantity(productOrderDTO.getQuantity());
        // Set other properties as needed
        return productOrder;
    }
}
