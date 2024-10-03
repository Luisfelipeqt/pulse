package pulse.com.br.app.api.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pulse.com.br.app.api.product.dtos.UserRequest;
import pulse.com.br.app.api.product.dtos.UserResponse;
import pulse.com.br.app.api.product.mappers.UserMapper;
import pulse.com.br.app.config.security.AuthToken;
import pulse.com.br.app.config.security.TokenUtil;
import pulse.com.br.app.core.entities.User;
import pulse.com.br.app.core.exceptions.ModelNotFoundException;
import pulse.com.br.app.core.exceptions.UserAlreadyExistException;
import pulse.com.br.app.core.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    @Override
    public UserResponse createUser(UserRequest userRequest) {
        var findUser = userRepository.findByLogin(userRequest.getLogin());
        if (findUser.isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String newPassword = encoder.encode(userRequest.getPassword());
            userRequest.setPassword(newPassword);
            var savedUser = userRepository.save(userMapper.toUser(userRequest));
            return userMapper.toUserResponse(savedUser);
        } else {
            throw new UserAlreadyExistException("Usuário já é cadastrado!");
        }
    }


    @Override
    public AuthToken signIn(User user) {

        var findUser = userRepository.findByLogin(user.getLogin());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (findUser.isPresent()) {
            if (encoder.matches(user.getPassword(), findUser.get().getPassword())) {
                return TokenUtil.encode(findUser.get());
            }
            throw new DataIntegrityViolationException("Senha incorreta!");
        }
        else {
            throw new ModelNotFoundException("Usuário não encontrado!");
        }
    }
}