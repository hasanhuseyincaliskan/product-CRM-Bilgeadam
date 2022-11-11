package com.example.primaryStore.controller;

import com.example.primaryStore.model.dto.request.CustomerAgeDTO;
import com.example.primaryStore.model.dto.CustomerDTO;
import com.example.primaryStore.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) {
        this.customerService.addCustomer(customerDTO);
        return new ResponseEntity<>("Müşteri ekleme işlemi başarılı.", HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerDTO customerDTO) {
        this.customerService.updateCustomer(customerDTO);
        return new ResponseEntity<>("Müşteri güncelleme işlemi başarılı.", HttpStatus.OK);
    }

    @DeleteMapping("/deleteBy{id}")
    public ResponseEntity<String> deleteCustomer(@RequestParam(name = "id") Long id) {
        this.customerService.deleteCustomer(id);
        return new ResponseEntity<>("Müşteri silme işlemi başarılı.", HttpStatus.OK);
    }

    @GetMapping("/getCustomers")
    public ResponseEntity<List<CustomerDTO>> getCustomers() {
        return new ResponseEntity<>(this.customerService.getCustomer(), HttpStatus.OK);
    }

    @GetMapping("/findBy{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@RequestParam(name = "id") Long id) {
        return new ResponseEntity<>(this.customerService.getCustomerById(id), HttpStatus.OK);
    }

    @GetMapping("/findByNameOrSurname")
    public ResponseEntity<List<CustomerDTO>> getCustomerByNameOrSurname(
            @RequestParam(name = "name") String name, @RequestParam(name = "surname") String surname) {
        return new ResponseEntity<>(this.customerService.getCustomerByNameOrSurname(name, surname), HttpStatus.OK);
    }

    @GetMapping("/findByAgeBetween")
    public ResponseEntity<List<CustomerDTO>> getCustomerByAgeBetween
            (@RequestParam(name = "firstAge") Integer firstAge, @RequestParam(name = "lastAge") Integer lastAge) {
        return new ResponseEntity<>(this.customerService.getCustomerByAgeBetween(firstAge, lastAge), HttpStatus.OK);
    }

    @GetMapping("/findCustomerCount")
    public ResponseEntity<Long> getCustomerByCount() {
        return new ResponseEntity<>(this.customerService.getAllCountInCustomer(), HttpStatus.OK);
    }

    @GetMapping("/findGroupByAge")
    public ResponseEntity<List<CustomerAgeDTO>> getGroupByAge() {
        return new ResponseEntity<>(this.customerService.getCustomerGroupByAge(), HttpStatus.OK);
    }

    @GetMapping("/ageFilterBy{name}")
    public ResponseEntity<List<Object>> getGroupByAgeFilterByNameWithHQL(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(this.customerService.getFilterByNameWithHQL(name), HttpStatus.OK);
    }

}
