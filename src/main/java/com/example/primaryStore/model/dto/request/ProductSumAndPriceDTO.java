package com.example.primaryStore.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSumAndPriceDTO {
    private Long count;
    private Double price;
    private String brand;
}
