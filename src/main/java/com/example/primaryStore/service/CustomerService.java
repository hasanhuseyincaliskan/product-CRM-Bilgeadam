package com.example.primaryStore.service;

import com.example.primaryStore.model.dto.request.CustomerAgeDTO;
import com.example.primaryStore.model.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void addCustomer(CustomerDTO customerDTO);

    void updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long id);

    List<CustomerDTO> getCustomer();

    CustomerDTO getCustomerById(Long id);

    List<CustomerDTO> getCustomerByNameOrSurname(String name, String surname);

    List<CustomerDTO> getCustomerByAgeBetween(Integer firstAge, Integer lastAge);

    Long getAllCountInCustomer();

    List<CustomerAgeDTO> getCustomerGroupByAge();

    List<Object> getFilterByNameWithHQL(String name);
}
