package Math.Project4.service;

import Math.Project4.models.MovStockModel;
import Math.Project4.models.ProductModel;
import Math.Project4.models.StockModel;
import Math.Project4.repositories.MovStockRepository;
import Math.Project4.repositories.ProductRepository;
import Math.Project4.requestDTO.ReqMoveProductsDTO;
import Math.Project4.responseDTO.Moviment.RespMovimentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class MovStockService {

    // Importar repositories
    @Autowired
    private final MovStockRepository movStockRepository;
    @Autowired
    private final ProductRepository productRepository;

    public MovStockService(MovStockRepository movStockRepository, ProductRepository productRepository){
        this.movStockRepository = movStockRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<RespMovimentDTO> movimentProductStock(ReqMoveProductsDTO moveProductsDTO){

        // Colcoar o objeto em uma variavel
        ProductModel productModel = productRepository.findByProId(moveProductsDTO.prodId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        // Atualizar quantidade do produto
        if("Entrada".equalsIgnoreCase(moveProductsDTO.movType())){
            productModel.setStkQuantity(productModel.getStkQuantity() + moveProductsDTO.quantity());
        } else if("Saída".equalsIgnoreCase(moveProductsDTO.movType())) {
            productModel.setStkQuantity(productModel.getStkQuantity() - moveProductsDTO.quantity());
            if(productModel.getStkQuantity() < 0){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade inferior a 0");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de moviemntação não encontrada");
        }

        // Salvar o produto com a nova quantidade
        productModel = productRepository.save(productModel);

        // Criar model de registro
        MovStockModel movStockModel = new MovStockModel();
        movStockModel.setProduct(productModel);
        movStockModel.setMovType(moveProductsDTO.movType());
        movStockModel.setQuantity(moveProductsDTO.quantity());
        movStockModel.setDateMov(moveProductsDTO.date());

        // Salvar o registro de movimentação
        movStockModel = movStockRepository.save(movStockModel);

        // Montar modelo de movimentação
        RespMovimentDTO response = new RespMovimentDTO(
                movStockModel.getMovId(),
                movStockModel.getMovType(),
                movStockModel.getProduct().getProId(),
                movStockModel.getProduct().getProName(),
                movStockModel.getProduct().getStkQuantity()
        );

        return ResponseEntity.ok().body(response);
    }

}
