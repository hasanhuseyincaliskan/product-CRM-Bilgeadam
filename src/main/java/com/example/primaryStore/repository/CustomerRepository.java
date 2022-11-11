package com.example.primaryStore.repository;

import com.example.primaryStore.model.dto.request.CustomerAgeDTO;
import com.example.primaryStore.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameOrSurname(String name, String surname);

    List<Customer> findByAgeBetween(Integer firstAge, Integer lastAge);

    Long countCustomer();
    @Query(name = "Customer.groupByAge", nativeQuery = true)
    List<CustomerAgeDTO> groupByAge();

    @Query(value = "select c.age, count(c) from "
            +"(select * from Customer as c1 where lower(c1.name) like lower(:name)) c group by c.age", nativeQuery = true)
    List<Object> groupByAgeFilterByNameWithHQL(@Param(value = "name") String name);

}
