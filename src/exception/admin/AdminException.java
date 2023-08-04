package exception.admin;

import exception.base.BaseException;
import exception.base.BaseExceptionType;
import lombok.Getter;

@Getter
public class AdminException extends BaseException {

    private BaseExceptionType baseExceptionType;

    public AdminException() {};

    public AdminException(BaseExceptionType baseExceptionType) {
        this.baseExceptionType = baseExceptionType;
    }
}
