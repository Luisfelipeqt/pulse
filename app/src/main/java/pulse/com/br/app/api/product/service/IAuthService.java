package pulse.com.br.app.api.product.service;

import pulse.com.br.app.api.product.dtos.UserRequest;
import pulse.com.br.app.api.product.dtos.UserResponse;
import pulse.com.br.app.config.security.AuthToken;
import pulse.com.br.app.core.entities.User;

public interface IAuthService {
    UserResponse createUser(UserRequest user);
    AuthToken signIn(User user);
}
