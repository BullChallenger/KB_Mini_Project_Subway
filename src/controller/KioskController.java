package controller;

import exception.NotMemberException;
import service.MemberService;
import service.OrderService;
import service.impl.MemberServiceImpl;
import service.impl.OrderServiceImpl;
import view.FailView;
import vo.OrderVo;

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
            memberService.findByPhoneNumber(memberPhoneNumber);
            // MenuView로 바로 갈지 or SuccessView 통해서 갈지 고려
            // 현재는 바로 MenuView로 호출

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
        orderService
    }

    public static void menuSelectByAll() {

    }

    public static void breadSelectByAll() {

    }

    public static void cheeseSelectByAll() {

    }

    public static void additionalMenuSelectByAll() {

    }

    public static void vegetableSelectByAll() {

    }

    public static void sourceSelectByAll() {

    }
}