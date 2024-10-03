package pulse.com.br.app.api.product.service;
import lombok.extern.log4j.Log4j2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pulse.com.br.app.api.product.dtos.ProductRequest;
import pulse.com.br.app.api.product.dtos.ProductResponse;
import pulse.com.br.app.api.product.mappers.ProductMapper;
import pulse.com.br.app.core.exceptions.ProductAlreadyExistException;
import pulse.com.br.app.core.exceptions.ProductNotFoundException;
import pulse.com.br.app.core.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;


    /**
     * Retrieve all products.
     *
     * @return a list of all products.
     */

    @Override
    public List<ProductResponse> findAllProducts() {
        try {
            var searchProducts = productRepository.findAllProducts();
            log.info("Buscando todos os produtos...");
            return searchProducts
                    .stream()
                    .map(productMapper::toProductResponse)
                    .toList();
        } catch (RuntimeException e) {
            log.info("Ocorreu um erro ao buscar os produtos: {}", e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Retrieve a product by its ID.
     *
     * @param id the ID of the product to retrieve.
     * @return the product with the specified ID.
     * @throws ProductNotFoundException if the product with the specified ID is not found.
     */

    @Override
    public ProductResponse findProductById(Long id) {
        var product = productRepository.findProductById(id);
        if (product.isPresent()){
            log.info("Produto com o ID: {} encontrado!", id);
            return productMapper.toProductResponse(product.get());
        }
        else {
            log.warn("Produto com o ID: {} não encontrado!", id);
            throw new ProductNotFoundException("Produto não encontrado.");
        }
    }

    /**
     * Create a new product.
     *
     * @param productRequest the product to create.
     * @throws ProductAlreadyExistException if a product with the same barcode or description already exists.
     */

    @Override
    public void createProduct(ProductRequest productRequest) {

            var searchProductByBarCode = productRepository.findProductByBarcode(productRequest.getBarcode());
            var searchProductByDescription = productRepository.findProductByDescription(productRequest.getDescription());

            if(searchProductByBarCode.isPresent() || searchProductByDescription.isPresent()){
                log.warn("Descrição ou Código de barra já cadastrados!");
                throw new ProductAlreadyExistException();
            }
            else {
                productRepository.save(productMapper.toProduct(productRequest));
                log.info("Criando um novo produto...");
            }
    }

    /**
     * Update an existing product.
     *
     * @param id the ID of the product to update.
     * @param productRequest the updated product information.
     * @throws ProductNotFoundException if the product with the specified ID is not found.
     * @throws ProductAlreadyExistException if a product with the same barcode or description already exists.
     */

    @Override
    public void updateProduct(Long id, ProductRequest productRequest) {
        var searchProductById = productRepository.findProductById(id);
        if (searchProductById.isEmpty()) {
            log.warn("Produto com o ID: {} não encontrado!", id);
            throw new ProductNotFoundException("Produto não encontrado.");
        }

        var product = searchProductById.get();

        if (productRequest.getDescription() != null) {
            product.setDescription(productRequest.getDescription());
        }
        if (productRequest.getBarcode() != null) {
            product.setBarcode(productRequest.getBarcode());
        }
        if (productRequest.getUnitPrice() != null) {
            product.setUnitPrice(productRequest.getUnitPrice());
        }
        if (productRequest.getUnitOfMeasure() != null) {
            product.setUnitOfMeasure(productRequest.getUnitOfMeasure());
        }

        if (!Objects.equals(product.getBarcode(), productRequest.getBarcode())) {
            var searchProductByBarCode = productRepository.findProductByBarcode(productRequest.getBarcode());
            if (searchProductByBarCode.isPresent()) {
                throw new ProductAlreadyExistException("Descrição já cadastrada!");
            }
        }

        if (!Objects.equals(product.getDescription(), productRequest.getDescription())) {
            var searchProductByDescription = productRepository.findProductByDescription(productRequest.getDescription());
            if (searchProductByDescription.isPresent()) {
                throw new ProductAlreadyExistException("Descrição já cadastrada!");
            }
        }
        productRepository.save(product);

        log.info("Atualizando produto com o ID: {}...", id);
        log.info("Código de barra: {}", product.getBarcode());
        log.info("Descrição: {}", product.getDescription());
    }

    /**
     * Soft delete a product by its ID.
     *
     * @param id the ID of the product to soft delete.
     * @throws ProductNotFoundException if the product with the specified ID is not found.
     */

    @Override
    public void softDeleteProduct(Long id) {
        var product = productRepository.findProductById(id);
        if (product.isPresent()){
            log.info("Produto com o ID: {} encontrado!", id);
            productRepository.softDeleteProduct(product.get().getId());
        }
        else {
            log.warn("Produto com o ID: {} não encontrado!", id);
            throw new ProductNotFoundException("Produto não encontrado.");
        }
    }

    /**
     * Retrieve a product by its description.
     *
     * @param description the description of the product to retrieve.
     * @return the product with the specified description.
     * @throws ProductNotFoundException if the product with the specified description is not found.
     */

    @Override
    public ProductResponse findProductByDescription(String description) {
        var searchProductByDescription = productRepository.findProductByDescription(description);
        if (searchProductByDescription.isPresent()){
            log.info("Produto com a descrição: {} encontrado!", description);
            return productMapper.toProductResponse(searchProductByDescription.get());
        }
        else {
            log.warn("Produto com a descrição: {} não encontrado!", description);
            throw new ProductNotFoundException("Produto não encontrado.");
        }
    }
}

