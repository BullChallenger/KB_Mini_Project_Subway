package view;

import controller.KioskController;
import dto.MemberDTO;
import exception.order.OrderException;
import vo.OrderVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KioskView {

    static Scanner sc = new Scanner(System.in);

    /***
     * 멤버의 경우, 전화 번호 입력 후 주문 메뉴로 이동
     */
    public static void getUserPhoneNumber() {
        System.out.println("멤버십 전화 번호 입력");
        System.out.print("입력 > ");
        String memberPhoneNumber = sc.nextLine();
        // 멤버인지 유효성 검사 필요
        // KioskController => (유효성 검사) => MenuView.startOrder() 호출
        KioskController.checkMember(memberPhoneNumber);
    }

    public static void startOrder(MemberDTO dto) {
        while (true) {
            System.out.print("[ 1. 주문하기   ");
            System.out.print("2. 주문현황 확인하기   ");
            System.out.print("3. 장바구니 결제하기   ");
            System.out.println("4. 종료 ]");
            System.out.print("입력 > ");
            try {
                int menu = Integer.parseInt(sc.nextLine());//
                switch (menu) {
                    case 1:
                        OrderVo vo = new OrderVo();
                        // 1-1 메뉴 전체 조회 후 출력
                        System.out.println("==================== 메뉴 선택 ====================");
                        int menuSize = KioskController.menuSelectByAll();

                        // 1-2 사용자 메뉴 입력 받기
                        System.out.print("입력 > ");
                        int userSelectMenu = Integer.parseInt(sc.nextLine());//
                        if (userSelectMenu > menuSize || userSelectMenu <= 0) throw new OrderException();
                        vo.setMenuId(userSelectMenu);

                        KioskController.getMemberOrderHistory(dto.getMemberId(), (long) menu);

                        // 2-1 빵 전체 조회 후 출력 및, 이전 히스토리가 있다면? 이전에 선택한 선택지 출력
                        System.out.println("==================== 빵 선택 ====================");
                        int breadSize = KioskController.breadSelectByAll();

                        // 2-2 사용자 빵 입력 받기
                        System.out.print("입력 > ");
                        int userSelectBread = Integer.parseInt(sc.nextLine());
                        if (userSelectBread > breadSize || userSelectBread <= 0) throw new OrderException();
                        vo.setSelectBread(userSelectBread);

                        // 3-1 치즈 출력
                        System.out.println("==================== 치즈 선택 ====================");
                        int cheeseSize = KioskController.cheeseSelectByAll();

                        // 3-2 치즈 입력
                        System.out.print("입력 > ");
                        int userSelectCheese = Integer.parseInt(sc.nextLine());
                        if (userSelectCheese > cheeseSize || userSelectCheese <= 0) throw new OrderException();
                        vo.setSelectCheese(userSelectCheese);

                        // 4-1 추가 선택 메뉴 출력
                        System.out.println("==================== 추가 메뉴 선택 ====================");
                        int additionalMenuSize =  KioskController.additionalMenuSelectByAll();
                        // 4-2 추가 선택 메뉴 입력
                        System.out.print("입력 > ");
                        String userSelectAdditionalMenu = sc.nextLine();
                        List<String> menuInputs = List.of(userSelectAdditionalMenu.split(" "));
                        if (menuInputs.stream().map(input -> Integer.parseInt(input))
                                .noneMatch(input -> input <= additionalMenuSize && input > 0)) {
                            throw new OrderException();
                        }

                        vo.setSelectedAdditionalMenu(userSelectAdditionalMenu);

                        // 5-1 전체 채소 출력
                        System.out.println("==================== 제외 채소 선택 ====================");
                        int excludeVegeSize = KioskController.vegetableSelectByAll();

                        // 5-2 제외 채소 입력
                        System.out.print("입력 > ");
                        String userSelectExcludeVegetable = sc.nextLine();
                        vo.setExcludedVegetable(userSelectExcludeVegetable);
                        List<String> vegeInputs = List.of(userSelectExcludeVegetable.split(" "));
                        if (vegeInputs.stream().map(input -> Integer.parseInt(input))
                                .noneMatch(input -> input <= excludeVegeSize && input > 0)) {
                            throw new OrderException();
                        }
                        // 6-1 소스 출력
                        System.out.println("==================== 소스 선택 ====================");
                        int sourceSize = KioskController.sourceSelectByAll();

                        // 6-2 소스 입력
                        System.out.print("입력 > ");
                        String userSelectSource = sc.nextLine();
                        vo.setSelectedSource(userSelectSource);
                        List<String> sourceInputs = List.of(userSelectSource.split(" "));
                        if (sourceInputs.stream().map(input -> Integer.parseInt(input))
                                .noneMatch(input -> input <= sourceSize && input > 0)) {
                            throw new OrderException();
                        }
                        KioskController.order(dto.getMemberId(), vo);
                        break;
                    case 2:
                        KioskController.orderSelectByAll();
                        break;
                    case 3:
                        KioskController.cartPayment(dto.getMemberId());
                        break;
                    case 4:
                        System.out.println("다음에 다시 만나요~~^^ 로그아웃됩니다...");
                        return;
                    default:
                        System.out.println("잘못되었습니다..다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("메뉴는 숫자만 가능합니다.");
            } catch (NullPointerException e) {
                throw new OrderException();
            } catch (OrderException e) {
                throw new OrderException();
            }
        }//while문
    }

    public static void printPoint(Long memberId) {
        while (true) {
            System.out.print("[ 1. 메뉴담기   ");
            System.out.print("2. 결제하기   ");
            System.out.print("3. 장바구니 결제하기   ");
            System.out.println("4. 종료 ]");
            System.out.print("입력 > ");
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

    public static void addCartOrPay(Long memberId, ArrayList<OrderVo> cart) {

        KioskController.findMenuByMenuId(cart);

        System.out.print("[ 1. 메뉴 담기   ");
        System.out.println("2. (장바구니) 결제하기 ]");
        System.out.print("입력 > ");

        try {
            int menu = Integer.parseInt(sc.nextLine());//
            switch (menu) {
                case 1:
                    // 다시 주문 화면으로
                    break;
                case 2:
                    // 결제 창으로
                    KioskController.cartPayment(memberId);

                    break;
                default:
                    System.out.println("잘못되었습니다. 다시 입력해주세요.");
            }
        } catch (NumberFormatException e) {
            System.out.println("메뉴는 숫자만 가능합니다.");
        } catch (OrderException e) {
            throw new OrderException();
        }

    }
}