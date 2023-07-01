package com.khanfar.project2.Service;

import com.khanfar.project2.DTO.ProductDTO;
import com.khanfar.project2.Entity.Product;
import com.khanfar.project2.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::convertToDTO);
    }

    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setSlug(product.getSlug());
        productDTO.setName(product.getName());
        productDTO.setReference(product.getReference());
        productDTO.setPrice(product.getPrice());
        productDTO.setVat(product.getVat());
        productDTO.setStockable(product.getStockable());
        // Set other properties as needed
        return productDTO;
    }

    private Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setSlug(productDTO.getSlug());
        product.setName(productDTO.getName());
        product.setReference(productDTO.getReference());
        product.setPrice(productDTO.getPrice());
        product.setVat(productDTO.getVat());
        product.setStockable(productDTO.getStockable());
        // Set other properties as needed
        return product;
    }
}
