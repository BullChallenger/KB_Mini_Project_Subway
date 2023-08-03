package view;

import controller.KioskController;
import dto.MemberDTO;
import dto.MemberOrderDTO;
import dto.MenuDTO;
import vo.OrderVo;

import java.util.ArrayList;
import java.util.Scanner;

public class KioskView {

    static Scanner sc = new Scanner(System.in);

    /***
     * 멤버의 경우, 전화 번호 입력 후 주문 메뉴로 이동
     */
    public static void getUserPhoneNumber() {
        System.out.println("멤버십 전화 번호 입력");
        String memberPhoneNumber = sc.nextLine();
        // 멤버인지 유효성 검사 필요
        // KioskController => (유효성 검사) => MenuView.startOrder() 호출
        KioskController.checkMember(memberPhoneNumber);
    }

    public static void startOrder(MemberDTO dto) {
        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.print("[ 1. 주문하기   ");
            System.out.print("2. 주문현황 확인하기   ");
            System.out.print("3. 장바구니 결제하기   ");
            System.out.print("4. 종료 ]");
            System.out.println("\n--------------------------------------------");
            System.out.println("선택메뉴는?");
            try {
                int menu = Integer.parseInt(sc.nextLine());//
                switch (menu) {
                    case 1:
                        OrderVo vo = new OrderVo();
                        // 1-1 메뉴 전체 조회 후 출력
                        KioskController.menuSelectByAll();
                        // 1-2 사용자 메뉴 입력 받기
                        int userSelectMenu = Integer.parseInt(sc.nextLine());//
                        vo.setMenuId(userSelectMenu);

                        KioskController.getMemberOrderHistory(dto.getMemberId(), (long) menu);

                        // 2-1 빵 전체 조회 후 출력 및, 이전 히스토리가 있다면? 이전에 선택한 선택지 출력

                        KioskController.breadSelectByAll();
                        // 2-2 사용자 빵 입력 받기
                        int userSelectBread = Integer.parseInt(sc.nextLine());
                        vo.setSelectBread(userSelectBread);

                        // 3-1 치즈 출력
                        KioskController.cheeseSelectByAll();
                        // 3-2 치즈 입력
                        int userSelectCheese = Integer.parseInt(sc.nextLine());
                        vo.setSelectCheese(userSelectCheese);

                        // 4-1 추가 선택 메뉴 출력
                        KioskController.additionalMenuSelectByAll();
                        // 4-2 추가 선택 메뉴 입력
                        String userSelectAdditionalMenu = sc.nextLine();
                        vo.setSelectedAdditionalMenu(userSelectAdditionalMenu);

                        // 5-1 전체 채소 출력
                        KioskController.vegetableSelectByAll();
                        // 5-2 제외 채소 입력
                        String userSelectExcludeVegetable = sc.nextLine();
                        vo.setExcludedVegetable(userSelectExcludeVegetable);

                        // 6-1 소스 출력
                        KioskController.sourceSelectByAll();
                        // 6-2 소스 입력
                        String userSelectSource = sc.nextLine();
                        vo.setSelectedSource(userSelectSource);

                        KioskController.order(dto.getMemberId(), vo);
                        break;
                    case 2:
                        KioskController.orderSelectByAll();
                        break;
                    case 3:
                        KioskController.cartPayment();
                        break;
                    case 4:
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

    public static void printPoint(Long memberId) {
        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.print("[ 1. 메뉴담기   ");
            System.out.print("2. 결제하기   ");
            System.out.print("3. 장바구니 결제하기   ");
            System.out.print("4. 종료 ]");
            System.out.println("\n--------------------------------------------");
            System.out.println("선택메뉴는?");
            try {
                int menu = Integer.parseInt(sc.nextLine());//
                switch (menu) {

                    default:
                        System.out.println("잘못되었습니다..다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("메뉴는 숫자만 가능합니다.");
            }
        }//while문
    }

    public static void addCartOrPay(Long memberId, ArrayList<MemberOrderDTO> cart) {
        System.out.println("\n----------------------------------------");
        KioskController.findMenuByMenuId(cart);

        System.out.print("[ 1. 메뉴 담기   ");
        System.out.print("2. (장바구니) 결제하기 ]");
        System.out.println("\n--------------------------------------------");
        System.out.println("선택메뉴는?");
        try {
            int menu = Integer.parseInt(sc.nextLine());//
            switch (menu) {
                case 1:
                    // 다시 주문 화면으로
                    break;
                case 2:
                    // 결제 창으로
                    break;
                default:
                    System.out.println("잘못되었습니다..다시 입력해주세요.");
            }
        } catch (NumberFormatException e) {
            System.out.println("메뉴는 숫자만 가능합니다.");
        }

    }
}