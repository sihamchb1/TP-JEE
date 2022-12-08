package com.example.inventoryservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface  ProductRepository extends JpaRepository<Product,Long> {

}