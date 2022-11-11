package com.example.primaryStore.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {
    @JsonProperty(value = "sale_id")
    private Long id;
    @JsonProperty(value = "product")
    private List<ProductDTO> productList;
    @JsonProperty(value = "customer")
    private CustomerDTO customer;
}
