package com.example.primaryStore.serviceImp;

import com.example.primaryStore.exception.apiException.ApiNotFoundException;
import com.example.primaryStore.model.dto.request.CustomerAgeDTO;
import com.example.primaryStore.model.dto.CustomerDTO;
import com.example.primaryStore.model.entity.Customer;
import com.example.primaryStore.repository.CustomerRepository;
import com.example.primaryStore.service.CustomerService;
import com.example.primaryStore.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapperUtil modelMapperUtil;

    @Override
    @Transactional
    public void addCustomer(CustomerDTO customerDTO) {
        Customer addCustomer = this.modelMapperUtil.convertToModel(customerDTO, Customer.class);
        addCustomer.setInsertDate(new Date());
        this.customerRepository.save(addCustomer);
    }

    @Override
    @Transactional
    public void updateCustomer(CustomerDTO customerDTO) {
        Customer customer = this.modelMapperUtil.convertToModel(customerDTO, Customer.class);
        customer.setId(customerDTO.getId());
        customer.setInsertDate(new Date());
        this.customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        if (id != null) {
            this.customerRepository.deleteById(id);
        } else {
            throw new ApiNotFoundException("Belirtilen ID'ye ait kullanıcı silinmiş yada kayıtlarda mevcut değil.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getCustomer() {
        List<CustomerDTO> customerList = this.modelMapperUtil.mapAll(this.customerRepository.findAll(), CustomerDTO.class);
        if (customerList.isEmpty()) {
            throw new ApiNotFoundException("Müşteri bulunamadı.");
        } else {
            return customerList;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDTO getCustomerById(Long id) {
        CustomerDTO customerId = this.modelMapperUtil.convertToModel(this.customerRepository.findById(id), CustomerDTO.class);
        if (customerId == null) {
            throw new ApiNotFoundException("Böyle bir ID mevcut değil.");
        } else {
            return customerId;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getCustomerByNameOrSurname(String name, String surname) {
        List<CustomerDTO> customerNameOrSurname = this.modelMapperUtil.mapAll(this.customerRepository.findByNameOrSurname(name, surname), CustomerDTO.class);
        if (customerNameOrSurname.isEmpty()) {
            throw new ApiNotFoundException("Belirtilen isime veya soyisime sahip bir müşteri bulunamadı.");
        } else {
            return customerNameOrSurname;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getCustomerByAgeBetween(Integer firstAge, Integer lastAge) {
        List<CustomerDTO> ageBetween = this.modelMapperUtil.mapAll
                (this.customerRepository.findByAgeBetween(firstAge, lastAge), CustomerDTO.class);
        if (ageBetween.isEmpty()) {
            throw new ApiNotFoundException("Belirtilen yaş aralığında kayıtlı müşteri bulunamadı.");
        } else {
            return ageBetween;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long getAllCountInCustomer() {
        Long customerCount = this.customerRepository.countCustomer();
        if (customerCount == null) {
            throw new ApiNotFoundException("Müşteri sayısı 0");
        } else {
            return customerCount;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerAgeDTO> getCustomerGroupByAge() {
        List<CustomerAgeDTO> groupByCustomerAge = this.modelMapperUtil
                .mapAll(customerRepository.groupByAge(), CustomerAgeDTO.class);
        if (groupByCustomerAge.isEmpty()) {
            throw new ApiNotFoundException("Müşteri bulunamadı.");
        } else {
            return groupByCustomerAge;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object> getFilterByNameWithHQL(String name) {
        List<Object> filterByName = this.customerRepository.groupByAgeFilterByNameWithHQL('%' + name + '%');
        if (filterByName.isEmpty()) {
            throw new ApiNotFoundException("Belirtilen isime sahip bir müşteri bulunamadı.");
        } else {
            return filterByName;
        }
    }
}
