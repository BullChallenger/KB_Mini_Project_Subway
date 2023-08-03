package exception;

/**
 * 사용자가 등록되어있지 않은 전화 번호로 인증을 하려 할 때 발생되는 오류
 */
public class NotMemberException extends RuntimeException {
    public NotMemberException() {}
    public NotMemberException(String message) {
        super(message);
    }

}
