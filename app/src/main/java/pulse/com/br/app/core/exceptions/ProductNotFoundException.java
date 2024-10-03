package pulse.com.br.app.core.exceptions;

public class ProductNotFoundException extends ModelNotFoundException{
    public ProductNotFoundException(){
        super("Produto não encontrado!");
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}