package com.vg.orderservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "/api/products")
public interface ProductClient {
    @GetMapping("/{productId}")
    Object getOrderDetails(@PathVariable Integer productId);
}