package com.example.inventoryservice;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner start(ProductRepository repository, RepositoryRestConfiguration configuration){
		configuration.exposeIdsFor(Product.class);
		return args -> {
   repository.save(new Product(null,"Ordinatuer",788,12));
   repository.save(new Product(null,"Imprimante",788,12));
   repository.save(new Product(null,"SmartPhone",788,12));
   repository.findAll().forEach(p->{System.out.println(p.getName());});
		};
	}


}
