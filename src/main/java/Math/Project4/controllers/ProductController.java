package Math.Project4.controllers;

import Math.Project4.requestDTO.ReqNewProductDTO;
import Math.Project4.responseDTO.Product.RespNewProductDTO;
import Math.Project4.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @Operation(summary = "Criar produto", description = "Criar um novo produto no sistema", tags = "Produto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Produto já possui cadastro"),
            @ApiResponse(responseCode = "404", description = "Fornecedor não encontrado"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado")
    })
    @PostMapping(value = "/create")
    public ResponseEntity<RespNewProductDTO> create(ReqNewProductDTO newProductDTO) throws IOException {

        return productService.createProduct(newProductDTO);

    }

}
