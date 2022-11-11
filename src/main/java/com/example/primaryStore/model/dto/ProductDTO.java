package com.example.primaryStore.model.dto;

import com.example.primaryStore.util.ToUpperCaseDeserializerUtil;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    @JsonDeserialize(using = ToUpperCaseDeserializerUtil.class)
    private String brand;
    private String description;
    private Double price;
    private Date insertDate;
}
