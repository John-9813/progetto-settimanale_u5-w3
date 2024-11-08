package johnoliveira.progetto_settimanale_u5_w3.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
