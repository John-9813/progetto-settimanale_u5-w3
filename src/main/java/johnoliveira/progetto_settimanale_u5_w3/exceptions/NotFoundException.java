package johnoliveira.progetto_settimanale_u5_w3.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Long userId) {
        super("Il record con id" + userId + " non è stato trovato");
    }
}
