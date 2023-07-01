package com.khanfar.project2.Controllers;

import com.khanfar.project2.DTO.StockDTO;
import com.khanfar.project2.Entity.Stock;
import com.khanfar.project2.Exception.NotFoundException;
import com.khanfar.project2.Service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<StockDTO>> getAllStocks() {
        List<StockDTO> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStockById(@PathVariable Integer id) {
        StockDTO stock = stockService.getStockById(id)
                .orElseThrow(() -> new NotFoundException("Stock not found with id: " + id));
        return ResponseEntity.ok(stock);
    }

    @PostMapping
    public ResponseEntity<StockDTO> createStock(@Valid @RequestBody StockDTO stock) {
        StockDTO createdStock = stockService.saveStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStock(@PathVariable Integer id, @Validated @RequestBody StockDTO stock) {
        StockDTO existingStock = stockService.getStockById(id)
                .orElseThrow(() -> new NotFoundException("Stock not found with id: " + id));

        // Update existingStock properties with stock request body

        StockDTO updatedStock = stockService.saveStock(existingStock);
        return ResponseEntity.ok(updatedStock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Integer id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
