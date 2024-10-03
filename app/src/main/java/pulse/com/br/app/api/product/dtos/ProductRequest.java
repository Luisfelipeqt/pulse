package pulse.com.br.app.api.product.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class ProductRequest {

    @NotNull(message = "Campo não pode ser nulo.")
    @NotBlank(message = "Campo não pode ser vazio ou em branco.")
    @Size(min = 3)
    private String description;

    @NotNull(message = "Campo não pode ser nulo.")
    @NotBlank(message = "Campo não pode ser vazio ou em branco.")
    @Size(min = 12, max = 13, message = "O código de barras deve ter entre 12 e 13 caracteres.")
    private String barcode;

    @NotNull(message = "Campo não pode ser nulo.")
    @Positive(message = "Campo deve ser maior que zero.")
    private BigDecimal unitPrice;

    @NotNull(message = "Campo não pode ser nulo.")
    @NotBlank(message = "Campo não pode ser vazio ou em branco.")
    private String unitOfMeasure;
}
