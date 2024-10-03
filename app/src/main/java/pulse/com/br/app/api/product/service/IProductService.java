package pulse.com.br.app.api.product.service;

import pulse.com.br.app.api.product.dtos.ProductRequest;
import pulse.com.br.app.api.product.dtos.ProductResponse;

import java.util.List;

public interface IProductService {

    List<ProductResponse> findAllProducts();

    ProductResponse findProductById(Long id);
    void createProduct(ProductRequest productRequest);
    void updateProduct(Long id, ProductRequest productRequest);
    void softDeleteProduct(Long id);
    ProductResponse findProductByDescription(String description);
}
