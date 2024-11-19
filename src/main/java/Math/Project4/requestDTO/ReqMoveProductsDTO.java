package Math.Project4.requestDTO;

import java.time.LocalDate;

public record ReqMoveProductsDTO(
        Long prodId,
        String movType,
        Integer quantity,
        LocalDate date
) {
}
