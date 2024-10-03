package pulse.com.br.app.api.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pulse.com.br.app.api.product.dtos.ErrorResponse;
import pulse.com.br.app.api.product.dtos.UserRequest;
import pulse.com.br.app.api.product.dtos.UserResponse;
import pulse.com.br.app.api.product.service.IAuthService;
import pulse.com.br.app.config.security.AuthToken;
import pulse.com.br.app.core.entities.User;
import pulse.com.br.app.core.repositories.UserRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User", description = "API de usuários")
public class UsersController {

    private final IAuthService authService;

    private final UserRepository userRepository;

    @Operation(summary = "Cadastra um novo usuário", method = "POST", tags = {"User"})
    @ApiResponses(value = {
            @ApiResponse(description = "Created", responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = UserResponse.class))
            }),
            @ApiResponse(description = "Conflict", responseCode = "409", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            }),

    })
    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.status(201).body(authService.createUser(userRequest));
    }

    @Operation(summary = "Realiza login com um usuário existente", method = "POST", tags = {"AuthToken"})
    @ApiResponses(value = {
            @ApiResponse(description = "OK", responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AuthToken.class))
            }),
            @ApiResponse(description = "Not Found", responseCode = "404", content = {
                    @Content(schema = @Schema(implementation = ErrorResponse.class))
            }),

    })
    @PostMapping("/login")
    public ResponseEntity<AuthToken> signIn(@RequestBody User user) {
        return ResponseEntity.ok(authService.signIn(user));
    }
}
