package com.example.primaryStore.controller;

import com.example.primaryStore.model.dto.ProductDTO;
import com.example.primaryStore.model.dto.request.ProductRequestDTO;
import com.example.primaryStore.model.dto.request.ProductSumAndPriceDTO;
import com.example.primaryStore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) {
        this.productService.addProduct(productDTO);
        return new ResponseEntity<>("Ürün ekleme işlemi başarılı.", HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO) {
        this.productService.updateProduct(productDTO);
        return new ResponseEntity<>("Ürün güncelleme işlemi başarılı.", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete{id}")
    public ResponseEntity<String> deleteProduct(@RequestParam(value = "id") Long id) {
        this.productService.deleteProduct(id);
        return new ResponseEntity<>("Ürün silme işlemi başarılı.", HttpStatus.OK);
    }

    @GetMapping(value = "/allProducts")
    public ResponseEntity<List<ProductDTO>> getProductList() {
        return new ResponseEntity<>(this.productService.getProducts(), HttpStatus.OK);
    }

    @PostMapping(value = "/addList")
    public ResponseEntity<String> addListProduct(@RequestBody List<ProductDTO> productDTOS) {
        this.productService.addListProduct(productDTOS);
        return new ResponseEntity<>("Toplu ürün ekleme işlemi başarılı.", HttpStatus.CREATED);
    }

    @GetMapping(value = "/getBy{brand}")
    public ResponseEntity<List<ProductDTO>> getProductListByBrand(@RequestParam(value = "brand") String brand) {
        return new ResponseEntity<>(this.productService.getFindByBrand(brand), HttpStatus.OK);
    }

    //Query
    @GetMapping(value = "/customFind")
    public ResponseEntity<List<ProductDTO>> getCustomFind() {
        return new ResponseEntity<>(this.productService.getCustomFind(), HttpStatus.OK);
    }

    @GetMapping(value = "/allCount")
    public ResponseEntity<Long> getCustomCount() {
        return new ResponseEntity<>(this.productService.getAllCountInProduct(), HttpStatus.OK);
    }

    //Markaya göre toplam sayısı ve toplam fiyatları
    @GetMapping(value = "/sumProductAndPrice")
    public ResponseEntity<List<ProductSumAndPriceDTO>> getSumCountAndProduct() {
        return new ResponseEntity<>(this.productService.getSumCountAndPrice(), HttpStatus.OK);
    }

    //Markaya ait ürünlerin fiyatları toplamı
    @GetMapping(value = "/sumProductsFilterBy{name}")
    public ResponseEntity<Long> sumProductsFilterByName(@RequestParam(name = "name") String name) {
        return new ResponseEntity<>(this.productService.sumProductsFilterByName(name), HttpStatus.OK);
    }


    //İki fiyat arasındaki ürünlerin listesi
    @GetMapping(value = "/betweenProductFilterByPrice")
    public ResponseEntity<List<ProductDTO>> betweenProductsFilterByPrice(
            @RequestParam(value = "firstPrice")Float firstPrice,
            @RequestParam(value = "lastPrice") Float lastPrice){
    return new ResponseEntity<>(this.productService.getBetweenProductFilterByPrice(firstPrice, lastPrice), HttpStatus.OK);
    }

    //Id olmadan client'e yansıtılacak kısım.
    @GetMapping(value = "/showProductToClient")
    public ResponseEntity<List<ProductRequestDTO>> productRequestAll(){
        return new ResponseEntity<>(this.productService.getProductRequest(), HttpStatus.OK);
    }
}
