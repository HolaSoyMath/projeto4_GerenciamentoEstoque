package Math.Project4.responseDTO.Stock;

import Math.Project4.responseDTO.Supplier.RespNewSupplierDTO;

import java.time.LocalDate;

public record RespProductInStockDTO(
        Long proId,
        String proName,
        String proDescription,
        Double proPrice,
        LocalDate proDate,
        Integer proQuantity,
        RespNewSupplierDTO supplier
) {
}
