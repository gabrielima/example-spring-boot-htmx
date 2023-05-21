package com.gabrielima.examplespringboothtmx.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielima.examplespringboothtmx.models.Product;
import com.gabrielima.examplespringboothtmx.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    ProductService productService;

    @BeforeEach
    void before() {
        when(productService.findAll()).thenReturn(List.of(
                new Product(1L, "Product 1", "Description Product 1", 100, 99.99),
                new Product(2L, "Product 2", "Description Product 2", 100, 99.99),
                new Product(3L, "Product 3", "Description Product 3", 100, 99.99)
        ));

        when(productService.find(any()))
                .thenReturn(Optional.of(new Product(1L, "Product 1", "Description Product 1", 100, 99.99)));

        when(productService.save(any()))
                .thenReturn(new Product(1L, "Product 1", "Description Product 1", 100, 99.99));
    }

    @Test
    void list() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void find() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/products/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void create() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .content(asJsonString(new Product(null, "Product 1", "Description Product 1", 100, 99.99)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void update() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .put("/products/1")
                        .content(asJsonString(new Product(1L, "Product Updated", "Description Product 1", 100, 99.99)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Product Updated"));
    }

    @Test
    void delete() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/products/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}