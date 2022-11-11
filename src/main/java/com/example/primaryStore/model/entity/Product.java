package com.example.primaryStore.model.entity;

import com.example.primaryStore.model.dto.request.ProductRequestDTO;
import com.example.primaryStore.model.dto.request.ProductSumAndPriceDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedQuery(name = "Product.countProduct",
        query = "select count(p) from Product as p")

@NamedNativeQuery(name = "Product.sumCountAndPrice",
        query = "select count(p.id) as count, sum(p.price) as price, p.brand as brand from product as p group by brand",
        resultSetMapping = "sumDTO")

@SqlResultSetMapping(name = "sumDTO", classes = @ConstructorResult(targetClass = ProductSumAndPriceDTO.class, columns = {
        @ColumnResult(name = "count", type = Long.class),
        @ColumnResult(name = "price", type = Double.class),
        @ColumnResult(name = "brand", type = String.class)}))

@NamedNativeQuery(name="product.betweenPrice", query = "select * from product as p where price between :firstPrice and :lastPrice",
        resultClass = Product.class)

@NamedNativeQuery(name = "Product.requestDTO", query = "select p.brand, p.description, p.price from product as p", resultSetMapping = "requestDTO")

@SqlResultSetMapping(name = "requestDTO", classes = @ConstructorResult(targetClass = ProductRequestDTO.class, columns = {
        @ColumnResult(name = "brand", type = String.class),
        @ColumnResult(name = "description", type = String.class),
        @ColumnResult(name = "price", type = Double.class)}))

@Table(name = "product")
public class Product extends BaseEntity {
    @Column(name = "brand")
    private String brand;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "sale_id", insertable = false, updatable = false)
    private Sale sale;

}
