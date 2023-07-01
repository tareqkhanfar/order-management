package com.khanfar.project2.Service;

import com.khanfar.project2.DTO.StockDTO;
import com.khanfar.project2.Entity.Stock;
import com.khanfar.project2.Repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService {
    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockDTO> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<StockDTO> getStockById(Integer id) {
        Optional<Stock> stock = stockRepository.findById(id);
        return stock.map(this::convertToDTO);
    }

    public StockDTO saveStock(StockDTO stockDTO) {
        Stock stock = convertToEntity(stockDTO);
        Stock savedStock = stockRepository.save(stock);
        return convertToDTO(savedStock);
    }

    public void deleteStock(Integer id) {
        stockRepository.deleteById(id);
    }

    private StockDTO convertToDTO(Stock stock) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setQuantity(stock.getQuantity());
        stockDTO.setUpdatedAt(stock.getUpdatedAt());
        // Set other properties as needed
        return stockDTO;
    }

    private Stock convertToEntity(StockDTO stockDTO) {
        Stock stock = new Stock();
        stock.setId(stockDTO.getId());
        stock.setQuantity(stockDTO.getQuantity());
        stock.setUpdatedAt(stockDTO.getUpdatedAt());
        // Set other properties as needed
        return stock;
    }
}
