package pulse.com.br.app.api.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pulse.com.br.app.api.product.dtos.ErrorResponse;
import pulse.com.br.app.api.product.dtos.ProductRequest;
import pulse.com.br.app.api.product.dtos.ProductResponse;
import pulse.com.br.app.api.product.service.IProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/produtos")
@Tag(name = "Product", description = "API de produtos")
public class ProductController {

    private final IProductService productService;


    /**
     * GET /api/v1/produtos : Retrieve all products.
     *
     * @return a list of all products.
     */

    @GetMapping()
    @Operation(summary = "Buscar todos os produtos", description = "Retorna uma lista com todos os produtos cadastrados", tags = {"Product"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProductResponse.class))
            }),
    })
    public ResponseEntity<List<ProductResponse>> searchAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllProducts());
    }

    /**
     * GET /api/v1/produtos/{id} : Retrieve a product by its ID.
     *
     * @param id the ID of the product to retrieve.
     * @return the product with the specified ID.
     */

    @GetMapping(value = "/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna um produto específico pelo ID", tags = {"Product"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProductResponse.class))
            }),
            @ApiResponse(description = "Produto não encontrado", responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    public ResponseEntity<ProductResponse> searchProductById(@PathVariable Long id) {
            return ResponseEntity.status(HttpStatus.OK).body(productService.findProductById(id));
    }

    /**
     * GET /api/v1/produtos/description/{description} : Retrieve a product by its description.
     *
     * @param description the description of the product to retrieve.
     * @return the product with the specified description.
     */

    @GetMapping(value = "/description/{description}")
    @Operation(summary = "Buscar produto por descrição", description = "Retorna um produto específico pela descrição", tags = {"Product"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProductResponse.class))
            }),
            @ApiResponse(description = "Produto não encontrado", responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    public ResponseEntity<ProductResponse> searchProductByDescription(@PathVariable(value = "description") String description) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductByDescription(description));
    }

    /**
     * POST /api/v1/produtos : Create a new product.
     *
     * @param productRequest the product to create.
     * @return the created product.
     */

    @PostMapping()
    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto", tags = {"Product"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = ProductRequest.class))
            }),
            @ApiResponse(description = "Produto já existe", responseCode = "409", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * PUT /api/v1/produtos/{id} : Update an existing product.
     *
     * @param id the ID of the product to update.
     * @param productRequest the updated product information.
     * @return the updated product.
     */

    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar um produto", description = "Atualiza um produto existente", tags = {"Product"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProductRequest.class))
            }),
            @ApiResponse(description = "Produto não encontrado", responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))

            }),
            @ApiResponse(description = "Descrição ou Código de barra já cadastrado!", responseCode = "400", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * DELETE /api/v1/produtos/{id} : Delete an existing product.
     *
     * @param id the ID of the product to delete.
     * @return a response indicating the result of the delete operation.
     */

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar um produto", description = "Deleta um produto existente", tags = {"Product"}, responses = {
            @ApiResponse(description = "Sucesso", responseCode = "204", content = @Content),
            @ApiResponse(description = "Produto não encontrado", responseCode = "404", content = @Content)
    })
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long id) {
        productService.softDeleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}