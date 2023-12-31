package com.khanfar.project2.Controllers;

import com.khanfar.project2.DTO.ProductOrderDTO;
import com.khanfar.project2.DTO.ProductOrderId;
import com.khanfar.project2.Entity.ProductOrder;
import com.khanfar.project2.Exception.NotFoundException;
import com.khanfar.project2.Service.ProductOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-orders")
public class ProductOrderController {
    @Autowired
    private  ProductOrderService productOrderService;



    @GetMapping
    public ResponseEntity<List<ProductOrderDTO>> getAllProductOrders() {
        List<ProductOrderDTO> productOrders = productOrderService.getAllProductOrders();
        return ResponseEntity.ok(productOrders);
    }

    @GetMapping("/{productId}/{orderId}")
    public ResponseEntity<ProductOrderDTO> getProductOrderById(@PathVariable Integer productId, @PathVariable Integer orderId) {
        ProductOrderDTO productOrder = productOrderService.getProductOrderById(new ProductOrderId(productId, orderId))
                .orElseThrow(() -> new NotFoundException("Product Order not found with productId: " + productId + " and orderId: " + orderId));
        return ResponseEntity.ok(productOrder);
    }

    @PostMapping
    public ResponseEntity<ProductOrderDTO> createProductOrder(@Valid @RequestBody ProductOrderDTO productOrder) {
        ProductOrderDTO createdProductOrder = productOrderService.saveProductOrder(productOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductOrder);
    }

    @PutMapping("/{productId}/{orderId}")
    public ResponseEntity<ProductOrderDTO> updateProductOrder(@PathVariable Integer productId, @PathVariable Integer orderId, @Validated @RequestBody ProductOrderDTO productOrder) {
        ProductOrderDTO existingProductOrder = productOrderService.getProductOrderById(new ProductOrderId(productId, orderId))
                .orElseThrow(() -> new NotFoundException("Product Order not found with productId: " + productId + " and orderId: " + orderId));

        // Update existingProductOrder properties with productOrder request body

        ProductOrderDTO updatedProductOrder = productOrderService.saveProductOrder(existingProductOrder);
        return ResponseEntity.ok(updatedProductOrder);
    }

    @DeleteMapping("/{productId}/{orderId}")
    public ResponseEntity<Void> deleteProductOrder(@PathVariable Integer productId, @PathVariable Integer orderId) {
        productOrderService.deleteProductOrder(new ProductOrderId(productId, orderId));
        return ResponseEntity.noContent().build();
    }
}
