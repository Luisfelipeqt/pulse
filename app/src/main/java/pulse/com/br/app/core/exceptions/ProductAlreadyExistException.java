package pulse.com.br.app.core.exceptions;


import org.springframework.dao.DataIntegrityViolationException;

public class ProductAlreadyExistException extends DataIntegrityViolationException {

    public ProductAlreadyExistException(){
        super("Descrição ou Código de barra já cadastrado!");
    }
    public ProductAlreadyExistException(String message) {
        super(message);
    }
}

