package luke.auctioshopproductapi.productCategory.service;

import luke.auctioshopproductapi.productCategory.model.ProductCategory;

import java.util.Set;

public interface ProductCategoryService {

    Set<ProductCategory> getCategories();
}
