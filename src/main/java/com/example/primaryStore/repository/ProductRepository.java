package com.example.primaryStore.repository;

import com.example.primaryStore.model.dto.request.ProductRequestDTO;
import com.example.primaryStore.model.dto.request.ProductSumAndPriceDTO;
import com.example.primaryStore.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByBrand(String brand);

    @Query(value = "SELECT p FROM Product AS p ORDER BY p.brand ASC")
    List<Product> customFindAll();

    Long countProduct();

    @Query(name = "Product.sumCountAndPrice", nativeQuery = true)
    List<ProductSumAndPriceDTO> sumCountAndPrice();

    @Query(value = "select sum(price) from product where brand like '%'||:name||'%'", nativeQuery = true)
    Long sumProductFilterByName(String name);

    @Query(name = "product.betweenPrice", nativeQuery = true)
    List<Product> betweenProductFilterByPrice(Float firstPrice, Float lastPrice);

    @Query(name="Product.requestDTO", nativeQuery = true)
    List<ProductRequestDTO> requestDTO();
}
