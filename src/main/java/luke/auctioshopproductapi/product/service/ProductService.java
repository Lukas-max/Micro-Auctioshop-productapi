package luke.auctioshopproductapi.product.service;

import luke.auctioshopproductapi.product.model.Product;
import luke.auctioshopproductapi.product.model.ProductRequest;
import luke.auctioshopproductapi.product.model.ProductStock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.Set;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Product getProductById(Long id);

    Page<Product> getProductsByProductCategoryId(Long id, Pageable pageable);

    Page<Product> getProductsByProductName(String name, Pageable pageable);

    void deleteProduct(Long id);

    Product persistProduct(ProductRequest productRequest) throws IOException;

    Product updateProduct(ProductRequest productRequest) throws IOException;

    void updateProductStock(Set<ProductStock> productStock);
}
