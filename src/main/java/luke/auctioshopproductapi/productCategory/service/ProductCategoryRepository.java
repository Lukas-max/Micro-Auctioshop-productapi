package luke.auctioshopproductapi.productCategory.service;

import luke.auctioshopproductapi.productCategory.model.ProductCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends PagingAndSortingRepository<ProductCategory, Long> {

    @Query("SELECT pc FROM ProductCategory pc")
    Optional<List<ProductCategory>> getCategories();
}
