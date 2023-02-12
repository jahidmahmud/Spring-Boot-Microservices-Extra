package com.inventoryservice;

import com.inventoryservice.model.Inventory;
import com.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(InventoryRepository inventoryRepository){
//        return args -> {
//            Inventory inventory=new Inventory();
//            inventory.setSkuCode("tttt23");
//            inventory.setQuantity(100);
//            Inventory inventory1=new Inventory();
//            inventory1.setSkuCode("1234bjit");
//            inventory1.setQuantity(2);
//            inventoryRepository.save(inventory);
//            inventoryRepository.save(inventory1);
//        };
//    }

}
