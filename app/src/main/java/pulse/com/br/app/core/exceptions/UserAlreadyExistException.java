package pulse.com.br.app.core.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class UserAlreadyExistException extends DataIntegrityViolationException {
    public UserAlreadyExistException() {
        super("Usuário já é cadastrado!");
    }

    public UserAlreadyExistException(String message) {
        super(message);
    }
}