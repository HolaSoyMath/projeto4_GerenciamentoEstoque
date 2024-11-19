package Math.Project4.controllers;

import Math.Project4.requestDTO.ReqMoveProductsDTO;
import Math.Project4.responseDTO.Moviment.RespMovimentDTO;
import Math.Project4.service.MovStockService;
import Math.Project4.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moviment")
public class MovStockController {

    private final MovStockService movStockService;

    public MovStockController(MovStockService movStockService){
        this.movStockService = movStockService;
    }

    @Operation(summary = "Criar movimentação", description = "Criar uma nova movimentação de quantidade no estoque", tags = "Movimentação")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Produto já possui cadastro"),
            @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado")
    })
    @PostMapping(value = "/create")
    public ResponseEntity<RespMovimentDTO> create(ReqMoveProductsDTO moveProductsDTO){

        return movStockService.movimentProductStock(moveProductsDTO);

    }

}
