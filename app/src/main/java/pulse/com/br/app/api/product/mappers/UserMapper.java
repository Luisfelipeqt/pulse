package pulse.com.br.app.api.product.mappers;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pulse.com.br.app.api.product.dtos.UserRequest;
import pulse.com.br.app.api.product.dtos.UserResponse;
import pulse.com.br.app.core.entities.User;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public User toUser(UserRequest userRequest) {
        return mapper.map(userRequest, User.class);
    }

    public UserResponse toUserResponse(User user) {
        return mapper.map(user, UserResponse.class);
    }
}
