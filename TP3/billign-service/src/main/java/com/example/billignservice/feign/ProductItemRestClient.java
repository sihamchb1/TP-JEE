package com.example.billignservice.feign;

import com.example.billignservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "PRODUCTS-SERVICE")
public interface ProductItemRestClient {

    @GetMapping(path = "/products")
    PagedModel<Product> pageProducts();

    @GetMapping(path = "/product/{id}")
    Product getProductById(@PathVariable Long id);
}
