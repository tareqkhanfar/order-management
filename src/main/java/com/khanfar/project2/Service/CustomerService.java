package com.khanfar.project2.Service;

import com.khanfar.project2.DTO.CustomerDTO;
import com.khanfar.project2.DTO.OrderDTO;
import com.khanfar.project2.Entity.Customer;
import com.khanfar.project2.Entity.Order;
import com.khanfar.project2.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CustomerDTO> getCustomerById(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(this::convertToDTO);
    }

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    public CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setBornAt(customer.getBornAt());

        return customerDTO;
    }

    public Customer convertToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setBornAt(customerDTO.getBornAt());

        return customer;
    }

    public OrderDTO convertOrderToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderedAt(order.getOrderedAt());
        // Set other properties as needed
        return orderDTO;
    }

    private Order convertOrderToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setOrderedAt(orderDTO.getOrderedAt());
        // Set other properties as needed
        return order;
    }
}
