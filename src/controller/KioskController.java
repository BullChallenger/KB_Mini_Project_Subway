package controller;

import dto.IngredientDTO;
import dto.MemberDTO;
import dto.MenuDTO;
import exception.NotMemberException;
import service.MemberService;
import service.OrderService;
import service.impl.MemberServiceImpl;
import service.impl.OrderServiceImpl;
import view.FailView;
import view.KioskView;
import view.SuccesssView;
import vo.OrderVo;

import java.util.List;
import java.util.Scanner;

public class KioskController {

    public static int additionalMenu;
    private static Scanner sc = new Scanner(System.in);
    private static MemberService memberService = MemberServiceImpl.getInstance();
    private static OrderService orderService = OrderServiceImpl.getInstance();


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
        } catch (NotMemberException e) {
            e.printStackTrace();
            FailView.errorMessage(e.getMessage());
        }
    }

    /**
     * 주문
     * @param memberPhoneNumber: 멤버 전화 번호 (멤버 식별)
     */
    public static void order(String memberPhoneNumber, OrderVo vo) {

    }

    /**
     * 주문 현황 확인
     */
    public static void orderSelectByAll() {

    }

    /**
     * 모든 메뉴 확인
     */
    public static void menuSelectByAll() {
        try {
            List<MenuDTO> allMenu = orderService.findAllMenu();
            SuccesssView.printAllMenu(allMenu);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 선택 가능한 빵 확인
     */
    public static void breadSelectByAll() {
        try {
            List<IngredientDTO> ingredientDTOS = orderService.findIngredientByIngredientCategory(1);
            SuccesssView.printSelect(ingredientDTOS);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 선택 가능한 치즈 확인
     */
    public static void cheeseSelectByAll() {
        try {
            List<IngredientDTO> ingredientDTOS = orderService.findIngredientByIngredientCategory(2);
            SuccesssView.printSelect(ingredientDTOS);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 선택 가능한 추가메뉴 확인
     */
    public static void additionalMenuSelectByAll() {
        try {
            List<IngredientDTO> ingredientDTOS = orderService.findIngredientByIngredientCategory(3);
            SuccesssView.printSelect(ingredientDTOS);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 제외 가능한 야채 확인
     */
    public static void vegetableSelectByAll() {
        try {
            List<IngredientDTO> ingredientDTOS = orderService.findIngredientByIngredientCategory(4);
            SuccesssView.printSelect(ingredientDTOS);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 선택 가능한 소스 확인
     */
    public static void sourceSelectByAll() {
        try {
            List<IngredientDTO> ingredientDTOS = orderService.findIngredientByIngredientCategory(5);
            SuccesssView.printSelect(ingredientDTOS);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void getMemberOrderHistory(Long memberId, long menuId) {
        
    }
}