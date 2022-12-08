package com.example.billignservice;

import com.example.billignservice.entities.Bill;
import com.example.billignservice.entities.ProductItem;
import com.example.billignservice.feign.CustomerRestClient;
import com.example.billignservice.feign.ProductItemRestClient;
import com.example.billignservice.model.Customer;
import com.example.billignservice.model.Product;
import com.example.billignservice.repositories.BillRepository;
import com.example.billignservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository itemRepository, CustomerRestClient customerRestClient, ProductItemRestClient productItemRestClient)
    {
        return  args -> {

            Customer customer=customerRestClient.getCustomerById(1L);
           Bill bill= billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));
            PagedModel<Product> products=productItemRestClient.pageProducts();
            products.forEach(product -> {
                ProductItem productItem=new ProductItem();
                productItem.setPrice(product.getPrice());
                productItem.setQuantity(1+ new Random().nextInt(100));
                productItem.setBill(bill);
                itemRepository.save(productItem);
            });
        };
    }
}
