package com.vg.orderservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8081/api/customers")
public interface CustomerClient {
    @GetMapping("/{customerId}")
    Object getCustomerDetails(@PathVariable String customerId);
}
