package Math.Project4.repositories;

import Math.Project4.models.MovStockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovStockRepository extends JpaRepository<MovStockModel, Long> {
}
