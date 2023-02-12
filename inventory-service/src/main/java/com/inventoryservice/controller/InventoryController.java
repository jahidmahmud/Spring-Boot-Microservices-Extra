package com.inventoryservice.controller;

import com.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    Logger log = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    InventoryService inventoryService;
    @GetMapping
    public ResponseEntity<List<InventoryResponse>> isInStock(@RequestParam List<String> skuCode) {
        try {
            return ResponseEntity.ok().body(inventoryService.isInStock(skuCode));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.noContent().build();
    }
}
