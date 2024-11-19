package Math.Project4.service;

import Math.Project4.models.StockModel;
import Math.Project4.repositories.StockRepository;
import Math.Project4.requestDTO.ReqNewStockDTO;
import Math.Project4.responseDTO.Stock.RespNewStockDTO;
import Math.Project4.responseDTO.Stock.RespProductInStockDTO;
import Math.Project4.responseDTO.Supplier.RespNewSupplierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {this.stockRepository = stockRepository; }

    public ResponseEntity<RespNewStockDTO> createStock(ReqNewStockDTO newStockDTO){

        // Verificar se o Stock já existe
        if(stockRepository.findByStkName(newStockDTO.stkName()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Estoque já cadastrado");
        }

        // Montar o modelo de cadastro estoque
        StockModel stockModel = new StockModel();
        stockModel.setStkName(newStockDTO.stkName());

        // Salvar
        stockModel = stockRepository.save(stockModel);

        // Transformar no DTO de retorno
        RespNewStockDTO response = new RespNewStockDTO(
                stockModel.getStkId(),
                stockModel.getStkName()
        );

        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<List<RespProductInStockDTO>> getProductsStock(String stkName){

        // Buscar o estoque pelo nome
        StockModel stockModel = stockRepository.findByStkName(stkName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado"));

        // Retornar os produtos
        List<RespProductInStockDTO> products = stockModel.getProducts().stream()
                .map(product -> {
                    // Criar o DTO do fornecedor, sem os produtos dele
                    RespNewSupplierDTO supplierDTO = new RespNewSupplierDTO(
                            product.getSupplierModel().getSupId(),
                            product.getSupplierModel().getSupName()
                    );

                    // Criar o DTO de retorno
                    return new RespProductInStockDTO(
                            product.getProId(),
                            product.getProName(),
                            product.getProDescription(),
                            product.getProPrice(),
                            product.getProDueDate(),
                            product.getStkQuantity(),
                            supplierDTO
                    );
                }).toList();

        return ResponseEntity.ok(products);
    }

}
