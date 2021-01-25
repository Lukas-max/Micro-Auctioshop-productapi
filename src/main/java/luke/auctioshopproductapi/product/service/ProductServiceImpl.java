package luke.auctioshopproductapi.product.service;

import luke.auctioshopproductapi.product.model.Product;
import luke.auctioshopproductapi.product.model.ProductRequest;
import luke.auctioshopproductapi.product.model.ProductStock;
import luke.auctioshopproductapi.productCategory.model.ProductCategory;
import luke.auctioshopproductapi.productCategory.service.ProductCategoryRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductCategoryRepository categoryRepository;
    private final ProductRepository productRepository;


    public ProductServiceImpl(ProductCategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    /**
     *
     * @return page of all Products.
     */
    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     *
     * @param id Product id
     * @return Product by it's ID.
     */
    @Override
    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Nie znaleziono produktu o Id: " + id));
    }

    /**
     *
     * @return a Page of Products selected by the ProductCategory Id. So it will return all the products in a category.
     * Like games category or electronics category.
     */
    @Override
    public Page<Product> getProductsByProductCategoryId(Long categoryId, Pageable pageable) {
        return productRepository.findProductsByProductCategoryId(categoryId, pageable);
    }

    /**
     *
     * @return a page of products selected by inserted name. Will return the products that contain the written phrase.
     */
    @Override
    public Page<Product> getProductsByProductName(String name, Pageable pageable) {
        return productRepository.findByNameContainsIgnoreCase(name, pageable);
    }

    /**
     * Complete deletion of a product from the database. ProductCategory is untouched.
     */
    @Override
    public void deleteProduct(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nie znaleziono produktu o Id: " + id);

        productRepository.deleteById(id);
    }

    /**
     * This will persist the added by the administrator product.
     * @param productRequest is a DTO class that is then mapped to Product class.
     * @return The saved product.
     */
    @Override
    public Product persistProduct(ProductRequest productRequest) throws IOException {
        Product product = formatProduct(productRequest);
        return productRepository.save(product);
    }

    /**
     * This will format ProductRequest (Dto) to Product using method below. Then, if added new image it will
     * save it with the image, or if the image is null, it will persist the object without
     * persisting the image field.
     */
    @Override
    public Product updateProduct(ProductRequest productRequest) throws IOException {
        boolean isImageChanged = productRequest.getProductImage() != null;
        Product product = formatProductForUpdate(productRequest, isImageChanged);

        if (isImageChanged){
            productRepository.save(product);
        }else{
            productRepository.saveProductWithoutImage(
                    product.getProductId(),
                    product.getSku(),
                    product.getName(),
                    product.getDescription(),
                    product.getUnitPrice(),
                    product.isActive(),
                    product.getUnitsInStock(),
                    product.getDateTimeCreated(),
                    product.getDateTimeUpdated(),
                    product.getProductCategory());
        }
        return product;
    }

    /**
     * This method patches the Product units in stock, or active field. This happens when the customer buys products
     * and this method is called to decrease the stock levels.
     */
    @Override
    public void updateProductStock(Set<ProductStock> productStock) {
        if (productStock.size() == 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Nie znaleziono produktów w wysłanym zbiorze.");

        productStock.forEach(prod -> productRepository.patchStockAfterBuy(
                prod.getProductId(),
                prod.isActive(),
                prod.getUnitsInStock()));
    }

    /**
     *
     * @return Product.class from formatting ProductRequest. Product has an image wrapped in byte[].
     * Whereas ProductRequest has base64 data String in its place, that we must split and decode.
     */
    protected Product formatProduct(ProductRequest request) throws IOException {
        return new Product.ProductBuilder()
                .buildSku(request.getSku())
                .buildName(request.getName())
                .buildDescription(request.getDescription())
                .buildUnitPrice(request.getUnitPrice())
                .buildProductImage(getByteArray(request.getProductImage(), true))
                .buildActive(isActive(request.getUnitsInStock()))
                .buildUnitsInStock(request.getUnitsInStock())
                .buildDateTimeCreated(request.getDateTimeCreated())
                .buildDateTimeUpdated(null)
                .buildProductCategory(getProductCategory(request.getProductCategoryId()))
                .build();
    }

    /**
     * Method similar to that above. Just meant to work with updating a product.
     *  -> Product.isActive is set on the client side depending on the number of products.
     */
    protected Product formatProductForUpdate(ProductRequest request, boolean isImageChanged)
            throws IOException{
        return new Product.ProductBuilder()
                .buildId(request.getProductId())
                .buildSku(request.getSku())
                .buildName(request.getName())
                .buildDescription(request.getDescription())
                .buildUnitPrice(request.getUnitPrice())
                .buildProductImage(getByteArray(request.getProductImage(), isImageChanged))
                .buildActive(isActive(request.getUnitsInStock()))
                .buildUnitsInStock(request.getUnitsInStock())
                .buildDateTimeCreated(request.getDateTimeCreated())
                .buildDateTimeUpdated(request.getDateTimeUpdated())
                .buildProductCategory(getProductCategory(request.getProductCategoryId()))
                .build();
    }

    /**
     * Product is in @ManyToOne relationship with ProductCategory.
     */
    private ProductCategory getProductCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Could not fetch product category from database."));
    }

    /**
     * If unitsInStock = 0. Product.active = false;
     */
    private boolean isActive(int unitsInStock){
        return unitsInStock > 0;
    }

    /**
     * If boolean newImage true - that means one of these:
     *  a) It is a new Product so we need to add the image. Image from user, or standard image.
     *  b) We are updating an old product with a new image.
     *
     *  And if we are updating the product without changing the image then newImage equals false.
     */
    private byte[] getByteArray(String base64data, boolean newImage) throws IOException {
        if (newImage) {
            if (base64data != null)
                return Base64.getDecoder().decode(base64data.split(",")[1]);
            else
                return getStandardImage();
        }
        return null;
    }

    /**
     *
     * @return standard image from folder.
     * It's works when user didn't send and an image when adding a product.
     * And it's set to protected for usability in ProductServiceTest.class.
     */
    protected byte[] getStandardImage() throws IOException {
        Resource resource = new ClassPathResource("/static/empty.jpg");
        return resource.getInputStream().readAllBytes();
    }
}
