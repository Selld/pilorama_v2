package custom_exceptions;

/**
 * Created by selld on 24.10.16.
 */
public class InvalidUserException extends Throwable {
    public InvalidUserException(String message) {
        super(message);
    }
}
