package controller;

import dto.IngredientDTO;
import dto.MemberDTO;
import dto.MemberOrderDTO;
import dto.MenuDTO;
import service.AdminService;
import exception.member.MemberException;
import exception.order.OrderException;
import service.MemberService;
import service.OrderService;
import service.impl.AdminServiceImpl;
import service.impl.MemberServiceImpl;
import service.impl.OrderServiceImpl;
import view.FailView;
import view.KioskView;
import view.SuccesssView;
import vo.HistoryVo;
import vo.OrderVo;

import java.util.*;

import static exception.constant.MemberExceptionType.NOT_FOUND_MEMBER_ERROR;
import static exception.constant.OrderExceptionType.*;

public class KioskController {

    public static int additionalMenu;
    private static Scanner sc = new Scanner(System.in);
    private static MemberService memberService = MemberServiceImpl.getInstance();
    private static OrderService orderService = OrderServiceImpl.getInstance();
    private static AdminService adminService = AdminServiceImpl.getInstance();

    private static ArrayList<OrderVo> cart = new ArrayList<>();

    /**
     * 멤버 유효성 검사
     * 유효성 검사 통과 시, menu choice 화면으로 이동
     * @param memberPhoneNumber
     */
    public static void checkMember(String memberPhoneNumber) {
        try {
            MemberDTO dto = memberService.findByPhoneNumber(memberPhoneNumber);
            // MenuView로 바로 갈지 or SuccessView 통해서 갈지 고려
            // 현재는 바로 MenuView로 호출
            KioskView.startOrder(dto);
        } catch (MemberException e) {
            FailView.errorMessage(NOT_FOUND_MEMBER_ERROR.getErrorCode(), NOT_FOUND_MEMBER_ERROR.getErrorMessage());
        } catch (OrderException e) {
            FailView.errorMessage(NOT_FOUND_MENU_ERROR.getErrorCode(), NOT_FOUND_MENU_ERROR.getErrorMessage());
        }
    }

    /**
     * 주문
     *
     */
    public static void order(Long memberId, OrderVo vo) {
        try {
            cart.add(vo);
            KioskView.addCartOrPay(memberId, cart);
        } catch (OrderException e) {
            FailView.errorMessage(NOT_FOUND_MENU_ERROR.getErrorCode(), NOT_FOUND_MENU_ERROR.getErrorMessage());
        }
    }

    /**
     * 주문 현황 확인
     */
    public static void orderSelectByAll() {
        try {
            List<MemberOrderDTO> allOrderInfo = orderService.findAllOrderInfo();
            SuccesssView.printOrderStatus(allOrderInfo);
        } catch (OrderException e) {
            FailView.errorMessage(NOT_FOUND_ORDER_LIST.getErrorCode(), NOT_FOUND_ORDER_LIST.getErrorMessage());
        }
    }

    /**
     * 모든 메뉴 확인
     */
    public static void menuSelectByAll() {
        try {
            List<MenuDTO> allMenu = orderService.findAllMenu();
            SuccesssView.printAllMenu(allMenu);
        } catch (OrderException e) {
            FailView.errorMessage(NOT_FOUND_MENU_LIST.getErrorCode(), NOT_FOUND_MENU_LIST.getErrorMessage());
        }
    }

    /**
     * 선택 가능한 빵 확인
     */
    public static int breadSelectByAll() {
        try {
            List<IngredientDTO> ingredientDTOS = orderService.findIngredientByIngredientCategory(1);
            SuccesssView.printSelect(ingredientDTOS);
            return ingredientDTOS.size();
        } catch (OrderException e) {
            FailView.errorMessage(NOT_FOUND_INGREDIENT_LIST.getErrorCode(), NOT_FOUND_INGREDIENT_LIST.getErrorMessage());
        }
        return -1;
    }

    /**
     * 선택 가능한 치즈 확인
     */
    public static int cheeseSelectByAll() {
        try {
            List<IngredientDTO> ingredientDTOS = orderService.findIngredientByIngredientCategory(2);
            SuccesssView.printSelect(ingredientDTOS);
            return ingredientDTOS.size();
        } catch (OrderException e) {
            FailView.errorMessage(NOT_FOUND_INGREDIENT_LIST.getErrorCode(), NOT_FOUND_INGREDIENT_LIST.getErrorMessage());
        }
        return -1;
    }

    /**
     * 선택 가능한 추가메뉴 확인
     */
    public static int additionalMenuSelectByAll() {
        try {
            List<IngredientDTO> ingredientDTOS = orderService.findIngredientByIngredientCategory(3);
            SuccesssView.printSelect(ingredientDTOS);
            return ingredientDTOS.size();
        } catch (OrderException e) {
            FailView.errorMessage(NOT_FOUND_INGREDIENT_LIST.getErrorCode(), NOT_FOUND_INGREDIENT_LIST.getErrorMessage());
        }
        return -1;
    }

    /**
     * 제외 가능한 야채 확인
     */
    public static int vegetableSelectByAll() {
        try {
            List<IngredientDTO> ingredientDTOS = orderService.findIngredientByIngredientCategory(4);
            SuccesssView.printSelect(ingredientDTOS);
            return ingredientDTOS.size();
        } catch (OrderException e) {
            FailView.errorMessage(NOT_FOUND_INGREDIENT_LIST.getErrorCode(), NOT_FOUND_INGREDIENT_LIST.getErrorMessage());
        }
        return -1;
    }

    /**
     * 선택 가능한 소스 확인
     */
    public static int sourceSelectByAll() {
        try {
            List<IngredientDTO> ingredientDTOS = orderService.findIngredientByIngredientCategory(5);
            SuccesssView.printSelect(ingredientDTOS);
            return ingredientDTOS.size();
        } catch (OrderException e) {
            FailView.errorMessage(NOT_FOUND_INGREDIENT_LIST.getErrorCode(), NOT_FOUND_INGREDIENT_LIST.getErrorMessage());
        }
        return -1;
    }

    /**
     * 멤버의 해당 메뉴에 대한 과거 기록 조회
     * @param memberId
     * @param menuId
     */
    public static void getMemberOrderHistory(Long memberId, long menuId) {
        // orderService.() -- 추가할 내용 memberId, menuId => MemberOrderDTO 반환
        try {
            MemberOrderDTO history = orderService.findHistoryByMemberMenuId(memberId, menuId);
            String selectBread = adminService.findByIngredientId((long) history.getSelectBread()).getIngredientName();
            String selectCheese = adminService.findByIngredientId((long) history.getSelectCheese()).getIngredientName();

            StringTokenizer st = new StringTokenizer(history.getSelectedAdditionalMenu());
            StringBuilder additionalSB = new StringBuilder();
            while (st.hasMoreTokens()) {
                additionalSB.append(adminService.findByIngredientId(Long.parseLong(st.nextToken())).getIngredientName());
                additionalSB.append(",");
            }

            st = new StringTokenizer(history.getExcludedVegetable());
            StringBuilder exvegeSB = new StringBuilder();
            while (st.hasMoreTokens()) {
                exvegeSB.append(adminService.findByIngredientId(Long.parseLong(st.nextToken())).getIngredientName());
                exvegeSB.append(",");
            }

            st = new StringTokenizer(history.getSelectedSource());
            StringBuilder sourceSB = new StringBuilder();
            while (st.hasMoreTokens()) {
                sourceSB.append(adminService.findByIngredientId(Long.parseLong(st.nextToken())).getIngredientName());
                sourceSB.append(",");
            }

            HistoryVo historyVo = new HistoryVo(
                    selectBread, selectCheese, additionalSB.toString(), exvegeSB.toString(), sourceSB.toString()
            );
            SuccesssView.printMemberOrderDTO(historyVo);
        } catch (OrderException e) {
            e.printStackTrace();
        }
    }

    /**
     * 결제하기
     */
    public static void cartPayment(long memberId) {

        MemberOrderDTO orderDTO;
        try {
            for (OrderVo vo : cart) {
                orderDTO = orderService.saveMemberOrder(new MemberOrderDTO(
                        (long) 0,
                        mapping(1, vo.getSelectBread()),
                        mapping(2, vo.getSelectCheese()),
                        mappingString(3, vo.getSelectedAdditionalMenu()),
                        mappingString(4, vo.getExcludedVegetable()),
                        mappingString(5, vo.getSelectedSource()),
                        null,
                        'N',
                        memberId,
                        (long) vo.getMenuId()));

            }
            cart.clear();
            SuccesssView.printMessageOrderSuccess("주문성공");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void findMenuByMenuId(ArrayList<OrderVo> cart) {
        ArrayList<MenuDTO> cartMenu = new ArrayList<>();
        try {
            for (OrderVo vo : cart) {
                MenuDTO menu = orderService.findMenuByMenuId((long) vo.getMenuId());
                cartMenu.add(menu);
            }
            SuccesssView.printMenuInfo(cartMenu);
        } catch (RuntimeException e) {
            throw new OrderException();
        }
    }

    private static int mapping(int category, int inputNum) {
        switch (category) {
            case 1:
                return inputNum;
            case 2:
                return inputNum + 6;
        }
        return -1;
    }

    private static String mappingString(int category, String inputSelect) {
        StringTokenizer st = new StringTokenizer(inputSelect);
        StringBuilder sb = new StringBuilder();
        switch (category) {
            case 3: // +9
                while (st.hasMoreTokens()) {
                    sb.append((Integer.parseInt(st.nextToken()) + 9));
                    sb.append(" ");
                }
            case 4: // +15
                while (st.hasMoreTokens()) {
                    sb.append((Integer.parseInt(st.nextToken()) + 15));
                    sb.append(" ");
                }
            case 5: // + 23
                while (st.hasMoreTokens()) {
                    sb.append((Integer.parseInt(st.nextToken()) + 23));
                    sb.append(" ");
                }
        }
        return sb.toString();
    }
}