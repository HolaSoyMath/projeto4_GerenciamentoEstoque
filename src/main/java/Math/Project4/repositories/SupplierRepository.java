package Math.Project4.repositories;

import Math.Project4.models.SupplierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierModel, Long> {
    Optional<SupplierModel> findBySupId(Long supId);
    Optional<SupplierModel> findBySupName(String supName);
}
