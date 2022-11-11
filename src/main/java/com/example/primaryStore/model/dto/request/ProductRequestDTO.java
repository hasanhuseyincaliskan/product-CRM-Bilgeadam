package com.example.primaryStore.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {
    private String brand;
    private String description;
    private Double price;
}
