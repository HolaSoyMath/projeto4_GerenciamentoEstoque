package Math.Project4.requestDTO;

import java.time.LocalDate;

public record ReqNewProductDTO(
        String name,
        String description,
        Double price,
        LocalDate date,
        Integer quantity,
        Long supplier,
        Long stock
) {
}
