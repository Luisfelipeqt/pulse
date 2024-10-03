package pulse.com.br.app.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pulse.com.br.app.core.entities.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Retrieve all products that are not soft deleted.
     *
     * @return a list of all products with no deleted_at timestamp.
     */

    @Query("SELECT p FROM Product p WHERE p.deleted_at IS NULL")
    List<Product> findAllProducts();

    /**
     * Retrieve a product by its ID if it is not soft deleted.
     *
     * @param id the ID of the product to retrieve.
     * @return an Optional containing the product if found, or empty if not found.
     */

    @Query("SELECT p FROM Product p WHERE p.id = :id AND p.deleted_at IS NULL")
    Optional<Product> findProductById(Long id);

    /**
     * Soft delete a product by setting its deleted_at timestamp.
     *
     * @param id the ID of the product to soft delete.
     */

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.deleted_at = CURRENT_TIMESTAMP WHERE p.id = :id AND p.deleted_at IS NULL")
    void softDeleteProduct(Long id);

    /**
     * Retrieve a product by its barcode if it is not soft deleted.
     *
     * @param barcode the barcode of the product to retrieve.
     * @return an Optional containing the product if found, or empty if not found.
     */

    @Query("SELECT p FROM Product p WHERE p.barcode = :barcode AND p.deleted_at IS NULL")
    Optional<Product> findProductByBarcode(String barcode);

    /**
     * Retrieve a product by its description if it is not soft deleted.
     *
     * @param description the description of the product to retrieve.
     * @return an Optional containing the product if found, or empty if not found.
     */

    @Query("SELECT p FROM Product p WHERE p.description = :description AND p.deleted_at IS NULL")
    Optional<Product> findProductByDescription(String description);
}
