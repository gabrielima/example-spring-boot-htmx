package com.gabrielima.examplespringboothtmx.repositories;

import com.gabrielima.examplespringboothtmx.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
