package com.gabrielima.examplespringboothtmx.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Field \"name\" is mandatory")
    private String name;

    @NotBlank(message = "Field \"description\" is mandatory")
    private String description;

    @NotNull(message = "Field \"quantity\" is mandatory")
    private Integer quantity;

    @NotNull(message = "Field \"price\" is mandatory")
    private Double price;
}
