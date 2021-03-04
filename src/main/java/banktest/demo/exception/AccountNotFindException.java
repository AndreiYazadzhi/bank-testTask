package banktest.demo.exception;

public class AccountNotFindException extends RuntimeException {
    public AccountNotFindException(String message) {
        super(message);
    }
}
