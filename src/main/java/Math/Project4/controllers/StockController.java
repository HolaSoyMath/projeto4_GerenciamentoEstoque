package Math.Project4.controllers;

import Math.Project4.requestDTO.ReqNewStockDTO;
import Math.Project4.responseDTO.Stock.RespNewStockDTO;
import Math.Project4.responseDTO.Stock.RespProductInStockDTO;
import Math.Project4.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @Operation(summary = "Criar Estoque", description = "Criar um novo estoque no sistema", tags = "Estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estoque criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Estoque já possui cadastro"),
    })
    @PostMapping(value = "/create")
    public ResponseEntity<RespNewStockDTO> create(ReqNewStockDTO newStockDTO){

        return stockService.createStock(newStockDTO);

    }

    @Operation(summary = "ProdutosEstoque", description = "Listar todos os produtos do estoque", tags = "Estoque")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista dos produtos"),
            @ApiResponse(responseCode = "409", description = "Estoque não encontrado"),
    })
    @GetMapping(value = "/allProducts")
    public ResponseEntity<List<RespProductInStockDTO>> allProducts(String stkName){
        return stockService.getProductsStock(stkName);
    }

}
