package com.example.primaryStore.model.entity;

import com.example.primaryStore.model.dto.request.CustomerAgeDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@org.hibernate.annotations.NamedQuery(name = "Customer.countCustomer",
        query = "select count(c) from Customer as c")

@NamedNativeQuery(name = "Customer.groupByAge",
        query = "select c.age as AGE, count(c) as COUNT from Customer c group by c.age",
        resultSetMapping = "groupByAgeDTO")

@SqlResultSetMapping(name = "groupByAgeDTO", classes = @ConstructorResult(targetClass = CustomerAgeDTO.class, columns = {
        @ColumnResult(name = "age", type = Integer.class),
        @ColumnResult(name = "count", type = Long.class)}))

@Table(name = "customer")
public class Customer extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private Integer age;
}
