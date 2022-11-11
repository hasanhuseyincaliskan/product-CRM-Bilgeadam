package com.example.primaryStore.controller;

import com.example.primaryStore.model.dto.SaleDTO;
import com.example.primaryStore.model.dto.request.SaleRequestDTO;
import com.example.primaryStore.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/sale")
public class SaleController {
    private SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> addSale(@RequestBody SaleRequestDTO saleRequestDTO) {
        this.saleService.addSale(
                saleRequestDTO.getCustomerRequestId(),
                saleRequestDTO.getProductListRequestId(),
                saleRequestDTO.getSaleRequestId());
        return new ResponseEntity<>("Kayıt işlemi başarılı.", HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updateSale(@RequestBody SaleRequestDTO saleRequestDTO) {
        //TODO:500 hatası atıyor.
        this.saleService.updateSale(
                saleRequestDTO.getCustomerRequestId(),
                saleRequestDTO.getProductListRequestId(),
                saleRequestDTO.getSaleRequestId());
        return new ResponseEntity<>("Güncelleme işlemi başarılı.", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete{id}")
    public ResponseEntity<String> deleteSale(@RequestParam(name = "id") Long id) {
        this.saleService.deleteSale(id);
        return new ResponseEntity<>("Silme işlemi başarılı.", HttpStatus.OK);
    }

    @GetMapping(value = "/getList")
    public ResponseEntity<List<SaleDTO>> getSaleList() {
        return new ResponseEntity<>(this.saleService.getSales(), HttpStatus.OK);
    }
}
