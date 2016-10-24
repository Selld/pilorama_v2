package custom_exceptions;

/**
 * Created by selld on 24.10.16.
 */
public class AlreadyExistsException extends Throwable {
    public AlreadyExistsException(String message) {
        super(message);
    }

}
