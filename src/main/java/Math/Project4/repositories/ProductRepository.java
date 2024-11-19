package Math.Project4.repositories;

import Math.Project4.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Optional<ProductModel> findByProId(Long id);
    Optional<ProductModel> findByProName(String name);
}
