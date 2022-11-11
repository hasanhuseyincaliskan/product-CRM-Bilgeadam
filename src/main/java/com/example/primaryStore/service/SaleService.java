package com.example.primaryStore.service;

import com.example.primaryStore.model.dto.SaleDTO;
import com.example.primaryStore.model.entity.Sale;

import java.util.List;

public interface SaleService {
    void addSale(Long customerId, List<Long> productId, Long saleId);

    void updateSale(Long customerId, List<Long> productId, Long saleId);

    void deleteSale(Long id);

    List<SaleDTO> getSales();
}
