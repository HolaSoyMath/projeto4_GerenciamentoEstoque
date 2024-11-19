package Math.Project4.responseDTO.Product;

import java.time.LocalDate;

public record RespNewProductDTO(
        Long id,
        String name,
        String description,
        Double price,
        LocalDate date,
        Integer quantity,
        RespSupplierDTO idSupplier,
        RespStockDTO idStock
) {
}