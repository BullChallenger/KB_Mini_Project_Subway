package exception.order;

import exception.base.BaseException;
import exception.base.BaseExceptionType;

public class OrderException extends BaseException {
    private BaseExceptionType baseExceptionType;

    public OrderException() {}
    public OrderException(BaseExceptionType baseExceptionType) {
        this.baseExceptionType = baseExceptionType;
    }
    public OrderException(String message) {
        super(message);
    }

    @Override
    protected BaseExceptionType getBaseExceptionType() {
        return this.baseExceptionType;
    }
}
