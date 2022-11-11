package com.example.primaryStore.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequestDTO {
    private Long saleRequestId;
    private Long customerRequestId;
    private List<Long> productListRequestId;
}
