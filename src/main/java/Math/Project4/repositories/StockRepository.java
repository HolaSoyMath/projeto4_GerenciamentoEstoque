package Math.Project4.repositories;

import Math.Project4.models.StockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository <StockModel, Long> {
    Optional<StockModel> findByStkId(Long stkId);
    Optional<StockModel> findByStkName(String stkIName);
}
