package pulse.com.br.app.api.product.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pulse.com.br.app.api.product.dtos.ProductRequest;
import pulse.com.br.app.api.product.dtos.ProductResponse;
import pulse.com.br.app.core.entities.Product;

@Component
@RequiredArgsConstructor
public class ProductMapper {


    private final ModelMapper mapper;

    public Product toProduct(ProductRequest productRequest) {
        return mapper.map(productRequest, Product.class);
    }

    public ProductResponse toProductResponse(Product product) {
        return mapper.map(product, ProductResponse.class);
    }
}
