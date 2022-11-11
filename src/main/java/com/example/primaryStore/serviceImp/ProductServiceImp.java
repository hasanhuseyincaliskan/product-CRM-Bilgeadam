package com.example.primaryStore.serviceImp;

import com.example.primaryStore.exception.apiException.ApiNotFoundException;
import com.example.primaryStore.model.dto.ProductDTO;
import com.example.primaryStore.model.dto.request.ProductRequestDTO;
import com.example.primaryStore.model.dto.request.ProductSumAndPriceDTO;
import com.example.primaryStore.model.entity.Product;
import com.example.primaryStore.repository.ProductRepository;
import com.example.primaryStore.service.ProductService;
import com.example.primaryStore.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Override
    @Transactional
    public void addProduct(ProductDTO productDTO) {
        Product product = this.modelMapperUtil.convertToModel(productDTO, Product.class);
        product.setInsertDate(new Date());
        this.productRepository.save(product);
    }

    @Override
    @Transactional
    public void updateProduct(ProductDTO productDTO) {
        Product product = this.modelMapperUtil.convertToModel(productDTO, Product.class);
        product.setId(productDTO.getId());
        product.setInsertDate(new Date());
        this.productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        if (id != null) {
            this.productRepository.deleteById(id);
        } else {
            throw new ApiNotFoundException("Belirtilen ID'ye ait ürün silinmiş ya da kayıtlarda mevcut değil.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getProducts() {
        List<ProductDTO> productList = this.modelMapperUtil
                .mapAll((List<Product>) this.productRepository.findAll(), ProductDTO.class);
        if (productList.isEmpty()) {
            throw new ApiNotFoundException("Ürün(ler) bulunamadı.");
        } else {
            return productList;
        }
    }

    @Override
    @Transactional
    public void addListProduct(List<ProductDTO> productDTOS) {
        List<Product> productList = new ArrayList<>();
        for (ProductDTO productDTO : productDTOS) {
            Product product = this.modelMapperUtil.convertToModel(productDTO, Product.class);
            product.setInsertDate(new Date());
            productList.add(product);
        }
        this.productRepository.saveAll(productList);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getFindByBrand(String brand) {
        List<ProductDTO> findProduct = this.modelMapperUtil
                .mapAll(this.productRepository.findByBrand(brand.toUpperCase()), ProductDTO.class);
        if (findProduct.isEmpty()) {
            throw new ApiNotFoundException("İstenilen markaya ait ürün(ler) bulunamadı.");
        } else {
            return findProduct;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getCustomFind() {
        return this.modelMapperUtil.mapAll(this.productRepository.customFindAll(), ProductDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getAllCountInProduct() {
        Long count = this.productRepository.countProduct();
        if (count == null) {
            throw new ApiNotFoundException("Mevcut ürün sayısı 0");
        } else {
            return count;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductSumAndPriceDTO> getSumCountAndPrice() {
        List<ProductSumAndPriceDTO> productSumAndPrice = this.productRepository.sumCountAndPrice();
        if(productSumAndPrice.isEmpty()){
            throw new ApiNotFoundException("Hesaplanacak ürün(ler) bulunamadı.");
        }else {
            return productSumAndPrice;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Long sumProductsFilterByName(String name) {
        return this.productRepository.sumProductFilterByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getBetweenProductFilterByPrice(Float firstPrice, Float lastPrice) {
        List<ProductDTO> productDTOS = this.modelMapperUtil
                .mapAll(this.productRepository.betweenProductFilterByPrice(firstPrice, lastPrice), ProductDTO.class);
        return productDTOS;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductRequestDTO> getProductRequest() {
        return this.productRepository.requestDTO();
    }

}
