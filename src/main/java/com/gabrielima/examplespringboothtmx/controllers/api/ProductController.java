package com.gabrielima.examplespringboothtmx.controllers.api;

import com.gabrielima.examplespringboothtmx.exceptions.NotFoundException;
import com.gabrielima.examplespringboothtmx.models.Product;
import com.gabrielima.examplespringboothtmx.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> list() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product find(@PathVariable("id") Long id) throws NotFoundException {
        return productService.find(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable("id") Long id, @RequestBody Product product) throws NotFoundException {
        productService.find(id).orElseThrow(NotFoundException::new);
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws NotFoundException {
        Product product = productService.find(id).orElseThrow(NotFoundException::new);
        productService.delete(product);
    }
}
