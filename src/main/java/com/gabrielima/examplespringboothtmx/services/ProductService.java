package com.gabrielima.examplespringboothtmx.services;

import com.gabrielima.examplespringboothtmx.models.Product;
import com.gabrielima.examplespringboothtmx.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> find(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete (Product product) {
        productRepository.delete(product);
    }
}
