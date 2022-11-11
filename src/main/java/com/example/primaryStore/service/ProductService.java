package com.example.primaryStore.service;

import com.example.primaryStore.model.dto.ProductDTO;
import com.example.primaryStore.model.dto.request.ProductRequestDTO;
import com.example.primaryStore.model.dto.request.ProductSumAndPriceDTO;

import java.util.List;

public interface ProductService {
    void addProduct(ProductDTO productDTO);

    void updateProduct(ProductDTO productDTO);

    void deleteProduct(Long id);

    List<ProductDTO> getProducts();

    void addListProduct(List<ProductDTO> productDTOS);

    List<ProductDTO> getFindByBrand(String brand);

    List<ProductDTO> getCustomFind();

    Long getAllCountInProduct();

    List<ProductSumAndPriceDTO> getSumCountAndPrice();

    Long sumProductsFilterByName(String name);

    List<ProductDTO> getBetweenProductFilterByPrice(Float firstPrice, Float lastPrice);

    List<ProductRequestDTO> getProductRequest();
}
