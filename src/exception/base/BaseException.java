package exception.base;

public class BaseException extends RuntimeException{

    private BaseExceptionType baseExceptionType;

    public BaseException() {}
    public BaseException(String message) {
        super(message);
    }

    protected BaseExceptionType getBaseExceptionType() {
        return this.baseExceptionType;
    };
}
