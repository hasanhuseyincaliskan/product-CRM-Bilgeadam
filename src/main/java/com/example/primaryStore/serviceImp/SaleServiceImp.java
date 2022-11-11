package com.example.primaryStore.serviceImp;

import com.example.primaryStore.model.dto.SaleDTO;
import com.example.primaryStore.model.entity.Customer;
import com.example.primaryStore.model.entity.Product;
import com.example.primaryStore.model.entity.Sale;
import com.example.primaryStore.repository.CustomerRepository;
import com.example.primaryStore.repository.ProductRepository;
import com.example.primaryStore.repository.SaleRepository;
import com.example.primaryStore.service.SaleService;
import com.example.primaryStore.util.ModelMapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class SaleServiceImp implements SaleService {
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final ModelMapperUtil modelMapperUtil;

    public SaleServiceImp(CustomerRepository customerRepository, ProductRepository productRepository, SaleRepository saleRepository, ModelMapperUtil modelMapperUtil) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.modelMapperUtil = modelMapperUtil;
    }

    @Override
    @Transactional
    public void addSale(Long customerId, List<Long> productId, Long saleId) {
        Sale sale = new Sale();
        Customer customer = this.customerRepository.findById(customerId).orElse(null);
        List<Product> productsId = this.productRepository.findAllById(productId);
        sale.setCustomer(customer);
        sale.setProductList(productsId);
        sale.setInsertDate(new Date());
        this.saleRepository.save(sale);
    }

    @Override
    @Transactional
    public void updateSale(Long customerId, List<Long> productId, Long saleId) {
        Sale sale = this.saleRepository.findById(saleId).orElse(new Sale());
        Customer customer = this.customerRepository.findById(customerId).orElse(null);
        List<Product> productList = this.productRepository.findAllById(productId);
        sale.setCustomer(customer);
        sale.setProductList(productList);
        this.saleRepository.save(sale);
    }

    @Override
    @Transactional
    public void deleteSale(Long id) {
        this.saleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleDTO> getSales() {
        List<Sale> allSales = this.saleRepository.findAll();
        return this.modelMapperUtil.mapAll(allSales, SaleDTO.class);
    }
}
