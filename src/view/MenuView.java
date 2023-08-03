package view;

import controller.AdminController;
import controller.KioskController;

import java.util.Scanner;

public class MenuView {
    static Scanner sc = new Scanner(System.in);

    /**
     * 메뉴
     */
    public static void menuChoice() {
        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.print("[ 1. 멤버십   ");
            System.out.print("2. 비회원   ");
            System.out.print("3. 관리자 로그인   ");
            System.out.print("4. 종료 ]");
            System.out.println("\n--------------------------------------------");
            System.out.println("선택메뉴는?");
            try {
                int menu = Integer.parseInt(sc.nextLine());//
                switch (menu) {
                    case 1:
                        getUserPhoneNumber();
                        break;
                    case 2:
                        KioskController.menuChoice(null);
                        break;
                    case 3:
                        getAdminPassword();
                        break;
                    case 4:
                        System.out.println("다음에 다시 만나요~~^^ 로그아웃됩니다...");
                        System.exit(0);
                    default:
                        System.out.println("잘못되었습니다..다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("메뉴는 숫자만 가능합니다.");
            }
        }//while문
    }

    /**
     * 관리자의 경우, 비밀번호 입력 후 관리자 메뉴로 이동
     */
    private static void getAdminPassword() {
        System.out.println("관리자 비밀번호 입력");
        String password = sc.nextLine();
        AdminController.menuChoice(password);
    }

    /***
     * 멤버의 경우, 전화 번호 입력 후 주문 메뉴로 이동
     */
    private static void getUserPhoneNumber() {
        System.out.println("멤버십 전화 번호 입력");
        String memberPhoneNumber = sc.nextLine();
        KioskController.menuChoice(memberPhoneNumber);
    }
}