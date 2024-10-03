package pulse.com.br.app.api.product.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {
    @NotNull(message = "Campo não pode ser nulo.")
    @NotBlank(message = "Campo não pode ser vazio ou em branco.")
    @Size(min = 3)
    private String name;
    @NotNull(message = "Campo não pode ser nulo.")
    @NotBlank(message = "Campo não pode ser vazio ou em branco.")
    @Size(min = 3)
    private String login;
    @NotNull(message = "Campo não pode ser nulo.")
    @NotBlank(message = "Campo não pode ser vazio ou em branco.")
    @Size(min = 9)
    private String password;
}
