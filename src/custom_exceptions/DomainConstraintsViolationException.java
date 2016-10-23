package custom_exceptions;

/**
 * Created by selld on 23.10.16.
 */
public class DomainConstraintsViolationException extends Exception {
    public DomainConstraintsViolationException(String message) {
        super(message);
    }
}
