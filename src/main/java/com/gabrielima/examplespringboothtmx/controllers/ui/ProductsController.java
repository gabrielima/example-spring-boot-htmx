package com.gabrielima.examplespringboothtmx.controllers.ui;

import com.gabrielima.examplespringboothtmx.exceptions.NotFoundException;
import com.gabrielima.examplespringboothtmx.models.Product;
import com.gabrielima.examplespringboothtmx.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/index";
    }

    @GetMapping("/new")
    public String showNewForm(Product product) {
        return "products/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) throws NotFoundException {
        Product product = productService.find(id).orElseThrow(NotFoundException::new);
        model.addAttribute("product", product);
        return "products/form";
    }

    @PostMapping("/save")
    public String saveProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "products/form";
        }

        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) throws NotFoundException {
        Product product = productService.find(id).orElseThrow(NotFoundException::new);
        productService.delete(product);
        return "redirect:/products";
    }
}
