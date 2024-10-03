package pulse.com.br.app.api.product.dtos;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class ProductResponse {

    private Long id;
    private String description;
    private String barcode;
    private BigDecimal unitPrice;
    private String unitOfMeasure;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
