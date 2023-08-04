package exception.member;

import exception.base.BaseException;
import exception.base.BaseExceptionType;

public class MemberException extends BaseException {

    private BaseExceptionType baseExceptionType;

    public MemberException() {}
    public MemberException(BaseExceptionType baseExceptionType) {
        this.baseExceptionType = baseExceptionType;
    }
    public MemberException(String message) {
        super(message);
    }

    @Override
    protected BaseExceptionType getBaseExceptionType() {
        return this.baseExceptionType;
    }
}
