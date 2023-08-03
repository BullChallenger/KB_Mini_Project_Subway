package view;

import controller.AdminController;
import controller.KioskController;

import java.util.Scanner;

public class AdminView {

    static Scanner sc = new Scanner(System.in);
    /**
     * 관리자의 경우, 비밀번호 입력 후 관리자 메뉴로 이동
     */
    public static void getAdminPassword() {
        System.out.println("관리자 비밀번호 입력");
        String password = sc.nextLine();
        AdminController.checkAdmin(password);
    }

    /**
     * 관리자 설정 메뉴로 이동
     */
    public static void startSetting() {

        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.print("[ 1. 주문상태 조회   ");
            System.out.print("2. 재고 관리   ");
            System.out.print("3. 종료 ]");
            System.out.println("\n--------------------------------------------");
            System.out.println("선택메뉴는?");
            try {
                int menu = Integer.parseInt(sc.nextLine());//
                switch (menu) {
                    case 1:
                        manageOrder();
                        break;
                    case 2:
//                        viewStockCrud();
                        manageStore();
                        break;
                    case 3:
                        System.out.println("다음에 다시 만나요~~^^ 로그아웃됩니다...");
                        return;
                    default:
                        System.out.println("잘못되었습니다..다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("메뉴는 숫자만 가능합니다.");
            }
        }//while문
    }

    private static void viewStockCrud() {
        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.print("[ 1. 재고상태 조회   ");
            System.out.print("2. 재고 상태 변경   ");
            System.out.print("3. 재고 삭제 ");
            System.out.print("4. 종료 ]");
            System.out.println("\n--------------------------------------------");
            try {
                int menu = Integer.parseInt(sc.nextLine());//
                switch (menu) {
                    case 1:
                        manageStore();
                        break;
                    case 2:

                        manageStore();
                        break;
                    case 3:
                        System.out.println(" 삭제");
                        return;
                    case 4:
                        System.out.println(" 로그아웃됩니다...");
                        return;
                    default:
                        System.out.println("잘못되었습니다..다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("요청을 처리할 수 없습니다.");
            }
        }

    }

    /**
     * 관리자의 매장 관리
     * - 재료 메뉴 레시피 CRUD
     */
    public static void manageStore() {
        System.out.println("재류 아이디 | 재료 이름 | 재고 | 재고 카테고리 ");
        AdminController.getStock();
//        for(IngredientDTO i : list){
//            System.out.println(i.toString());
        }


    /**
     * 관리자의 주문관리
     * -
     */
    public static void manageOrder() {
        // 현재 미처리 주문 확인
        KioskController.orderSelectByAll();
    }

}
