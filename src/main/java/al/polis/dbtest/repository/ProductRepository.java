package al.polis.dbtest.repository;

import al.polis.dbtest.model.Product;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByDescriptionLike(String s, Sort sortDir);
    
    @Query("select p from Product p where price > :price")
    List<Product> findExpensiveProducts(@Param("price") Double d);
}
