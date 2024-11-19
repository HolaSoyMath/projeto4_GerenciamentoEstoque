package Math.Project4.controllers;

import Math.Project4.requestDTO.ReqNewSupplierDTO;
import Math.Project4.responseDTO.Supplier.RespNewSupplierDTO;
import Math.Project4.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    @Operation(summary = "Criar Fornecedor", description = "Criar um novo fornecedor no sistema", tags = "Fornecedor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fornecedor criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Fornecedor já possui cadastro"),
    })
    @PostMapping(value = "/create")
    public ResponseEntity<RespNewSupplierDTO> create(ReqNewSupplierDTO newSupplierDTO){

        return supplierService.createSupplier(newSupplierDTO);

    }

}
