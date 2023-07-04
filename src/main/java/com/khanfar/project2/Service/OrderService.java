package com.khanfar.project2.Service;

import com.khanfar.project2.DTO.OrderDTO;
import com.khanfar.project2.Entity.Order;
import com.khanfar.project2.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    private  CustomerService customerService ;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }



    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<OrderDTO> getOrderById(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.map(this::convertToDTO);
    }

    public OrderDTO saveOrder(OrderDTO orderDTO) {
        Order order = convertToEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderedAt(order.getOrderedAt());
        orderDTO.setCustomer(customerService.convertToDTO(order.getCustomer()));
        // Set other properties as needed
        return orderDTO;
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setOrderedAt(orderDTO.getOrderedAt());
        order.setCustomer(customerService.convertToEntity(orderDTO.getCustomer()));
        // Set other properties as needed
        return order;
    }

}
