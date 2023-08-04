package exception.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberExceptionType {

    NOT_FOUND_MEMBER_ERROR(404, "해당 전화번호를 사용하는 회원을 찾을 수 없습니다.");

    private final int errorCode;
    private final String errorMessage;
}
