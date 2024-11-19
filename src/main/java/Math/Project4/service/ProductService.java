package Math.Project4.service;

import Math.Project4.models.ProductModel;
import Math.Project4.models.StockModel;
import Math.Project4.models.SupplierModel;
import Math.Project4.repositories.ProductRepository;
import Math.Project4.repositories.StockRepository;
import Math.Project4.repositories.SupplierRepository;
import Math.Project4.requestDTO.ReqNewProductDTO;
import Math.Project4.responseDTO.Product.RespStockDTO;
import Math.Project4.responseDTO.Product.RespSupplierDTO;
import Math.Project4.responseDTO.Product.RespNewProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

@Service
public class ProductService {

    // Importar os Repositories
    // Produtos
    @Autowired
    private final ProductRepository prodRepository;
    // Fornecedor
    @Autowired
    private final SupplierRepository supplierRepository;
    // Estoque
    @Autowired
    private final StockRepository stockRepository;



    public ProductService(ProductRepository prodRepository, SupplierRepository supplierRepository, StockRepository stockRepository){
        this.prodRepository = prodRepository;
        this.supplierRepository = supplierRepository;
        this.stockRepository = stockRepository;
    }

    // Criar novo produto no sistema
    public ResponseEntity<RespNewProductDTO> createProduct(ReqNewProductDTO newProductDTO)
        throws IOException{

        // Verificar se o produto com o nome já existe
        if(prodRepository.findByProName(newProductDTO.name()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome de produto já cadastrado");
        }
        // Obter o Supplier cadastrado
        Optional<SupplierModel> supplier = supplierRepository.findBySupId(newProductDTO.supplier());
        if(supplier.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado");
        }

        // Obter o Stock cadastrado
        Optional<StockModel> stock = stockRepository.findByStkId(newProductDTO.stock());
        if(stock.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado");
        }

        // Transformar o DTO em um Model para cadastrar no sistema
        ProductModel productModel = new ProductModel();
        productModel.setProName(newProductDTO.name());
        productModel.setProDescription(newProductDTO.description());
        productModel.setProPrice(newProductDTO.price());
        productModel.setProDueDate(newProductDTO.date());
        productModel.setStkQuantity(newProductDTO.quantity());

        // Associar o fornecedor e estoque
        productModel.setSupplierModel(supplier.get());
        productModel.setStockModel(stock.get());

        // Salvar o produto no repositório
        productModel = prodRepository.save(productModel);

        // Montar DTO de fornecedor e estoque
        RespSupplierDTO respSupplierDTO = new RespSupplierDTO(productModel.getSupplierModel().getSupId(), productModel.getSupplierModel().getSupName());
        RespStockDTO respStockDTO = new RespStockDTO(productModel.getStockModel().getStkId(), productModel.getStockModel().getStkName());

        // Montar DTO de retorno
        RespNewProductDTO response = new RespNewProductDTO(
                productModel.getProId(),
                productModel.getProName(),
                productModel.getProDescription(),
                productModel.getProPrice(),
                productModel.getProDueDate(),
                productModel.getStkQuantity(),
                respSupplierDTO,
                respStockDTO
        );

        return ResponseEntity.ok().body(response);
    }

}