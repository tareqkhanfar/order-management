package com.khanfar.project2.Controllers;

import com.khanfar.project2.DTO.OrderDTO;
import com.khanfar.project2.Entity.Order;
import com.khanfar.project2.Service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.khanfar.project2.Exception.NotFoundException;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private  OrderService orderService;



    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer id) {
        OrderDTO order = orderService.getOrderById(id)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + id));
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO order) {
        OrderDTO createdOrder = orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Integer id, @Validated @RequestBody OrderDTO order) {
        OrderDTO existingOrder = orderService.getOrderById(id)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + id));

        // Update existingOrder properties with order request body

        OrderDTO updatedOrder = orderService.saveOrder(existingOrder);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
