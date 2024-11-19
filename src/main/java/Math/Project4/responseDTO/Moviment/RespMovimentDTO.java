package Math.Project4.responseDTO.Moviment;

public record RespMovimentDTO(
        Long movId,
        String movType,
        Long proID,
        String proName,
        Integer proQuantity
) {
}
