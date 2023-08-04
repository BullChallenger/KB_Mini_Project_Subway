package exception.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum OrderExceptionType {
    NOT_FOUND_MENU_ERROR(405, "해당 메뉴를 찾을 수 없습니다."),
    NOT_FOUND_ORDER_LIST(406, "주문현황을 읽어올 수 없습니다. 관리자에게 문의 혹은 주문현황을 확인해주세요."),
    NOT_FOUND_MENU_LIST(406, "메뉴목록을 읽어올 수 없습니다. 관리자에게 문의 혹은 메뉴목록을 확인해주세요."),
    NOT_FOUND_INGREDIENT_LIST(407, "주문 가능한 재료 목록을 읽어올 수 없습니다. 관리자에게 문의 혹은 재료 목록을 확인해주세요."),

    ;

    private final int errorCode;
    private final String errorMessage;
}
