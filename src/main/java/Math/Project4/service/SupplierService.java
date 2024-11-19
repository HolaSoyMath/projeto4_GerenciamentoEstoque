package Math.Project4.service;

import Math.Project4.models.SupplierModel;
import Math.Project4.repositories.SupplierRepository;
import Math.Project4.requestDTO.ReqNewSupplierDTO;
import Math.Project4.responseDTO.Supplier.RespNewSupplierDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class SupplierService {

    @Autowired
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }

    public ResponseEntity<RespNewSupplierDTO> createSupplier(ReqNewSupplierDTO newSupplierDTO){

        // Verificar se o fornecedor já existe
        if (supplierRepository.findBySupName(newSupplierDTO.name()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Fornecedor já cadastrado");
        }

        // Montar o modelo de cadastro
        SupplierModel supplierModel = new SupplierModel();
        supplierModel.setSupName(newSupplierDTO.name());
        supplierModel.setProducts(new ArrayList<>());

        // Salvar o Fornecedor
        supplierModel = supplierRepository.save(supplierModel);


        // Montar modelo de resposta
        RespNewSupplierDTO response = new RespNewSupplierDTO(
                supplierModel.getSupId(),
                supplierModel.getSupName()
        );

        return ResponseEntity.ok().body(response);

    }

}
