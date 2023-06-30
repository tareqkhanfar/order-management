package com.khanfar.project2.Controllers;

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
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        Order order = orderService.getOrderById(id)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + id));
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        Order createdOrder = orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @Validated @RequestBody Order order) {
        Order existingOrder = orderService.getOrderById(id)
                .orElseThrow(() -> new NotFoundException("Order not found with id: " + id));

        // Update existingOrder properties with order request body

        Order updatedOrder = orderService.saveOrder(existingOrder);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
