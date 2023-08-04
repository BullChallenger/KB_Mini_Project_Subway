package view;

import dto.IngredientDTO;
import dto.MemberOrderDTO;
import dto.MenuDTO;
import vo.HistoryVo;

import java.util.ArrayList;
import java.util.List;

public class SuccesssView {

    /**
     * method printSelect: 재료정보 출력
     * @param ingredientDTOS 출력할 재료 DTO 리스트
     */
    public static void printSelect(List<IngredientDTO> ingredientDTOS) {
        int cnt = 1;

        if (ingredientDTOS.size() > 9) {
            for (int i = 0; i < 9; ++i) {
                if (i == 0) {
                    if (ingredientDTOS.get(i).getIngredientCategory() == 3) {
                        System.out.print("|"+(cnt++) + ": " + ingredientDTOS.get(i).getIngredientName()+"("+ingredientDTOS.get(i).getIngredientPrice()+"원)"+ "\t|");
                    } else {
                        System.out.print("|"+(cnt++) + ": " + ingredientDTOS.get(i).getIngredientName() + "\t|");
                    }
                } else {
                    if (ingredientDTOS.get(i).getIngredientCategory() == 3) {
                        System.out.print("\t"+(cnt++) + ": " + ingredientDTOS.get(i).getIngredientName()+"("+ingredientDTOS.get(i).getIngredientPrice()+"원)"+ "\t|");
                    } else {
                        System.out.print("\t"+(cnt++) + ": " + ingredientDTOS.get(i).getIngredientName() + "\t|");
                    }
                }
            }
            System.out.println();
            for (int i = 9; i < ingredientDTOS.size(); ++i) {
                if (i == 9) {
                    if (ingredientDTOS.get(i).getIngredientCategory() == 3) {
                        System.out.print("|"+(cnt++) + ": " + ingredientDTOS.get(i).getIngredientName()+"("+ingredientDTOS.get(i).getIngredientPrice()+"원)"+ "\t|");
                    } else {
                        System.out.print("|"+(cnt++) + ": " + ingredientDTOS.get(i).getIngredientName() + "\t|");
                    }
                } else {
                    if (ingredientDTOS.get(i).getIngredientCategory() == 3) {
                        System.out.print("\t"+(cnt++) + ": " + ingredientDTOS.get(i).getIngredientName()+"("+ingredientDTOS.get(i).getIngredientPrice()+"원)"+ "\t|");
                    } else {
                        System.out.print("\t"+(cnt++) + ": " + ingredientDTOS.get(i).getIngredientName() + "\t|");
                    }
                }
            }
        } else {
            for (int i = 0; i < ingredientDTOS.size(); ++i) {
                if (i == 0) {
                    if (ingredientDTOS.get(i).getIngredientCategory() == 3) {
                        System.out.print((cnt++) + ": " + ingredientDTOS.get(i).getIngredientName()+"("+ingredientDTOS.get(i).getIngredientPrice()+"원)"+ "\t|");
                    } else {
                        System.out.print((cnt++) + ": " + ingredientDTOS.get(i).getIngredientName() + "\t|");
                    }
                } else {
                    if (ingredientDTOS.get(i).getIngredientCategory() == 3) {
                        System.out.print("\t"+(cnt++) + ": " + ingredientDTOS.get(i).getIngredientName()+"("+ingredientDTOS.get(i).getIngredientPrice()+"원)"+ "\t|");
                    } else {
                        System.out.print("\t"+(cnt++) + ": " + ingredientDTOS.get(i).getIngredientName() + "\t|");
                    }
                }
            }
        }
        System.out.println();
    }

    /**
     * method printAllMenu: 매뉴정보 출력
     * @param allMenu 출력할 메뉴 DTO 리스트
     */
    public static void printAllMenu(List<MenuDTO> allMenu) {
        int cnt = 1;

        for (int i = 0; i < 9; ++i) {
            if (i == 0) {
                System.out.print(+(cnt++)+": "+allMenu.get(i).getMenuName()+"("+allMenu.get(i).getMenuPrice()+"원)"+"\t|");
            } else {
                System.out.print("\t"+(cnt++)+": "+allMenu.get(i).getMenuName()+"("+allMenu.get(i).getMenuPrice()+"원)"+"\t|");
            }
        }
        System.out.println();
        for (int i = 9; i < allMenu.size(); ++i) {
            if (i == 9) {
                System.out.print(+(cnt++)+": "+allMenu.get(i).getMenuName()+"("+allMenu.get(i).getMenuPrice()+"원)"+"\t|");
            } else {
                System.out.print("\t"+(cnt++)+": "+allMenu.get(i).getMenuName()+"("+allMenu.get(i).getMenuPrice()+"원)"+"\t|");
            }
        }
        System.out.println();
    }

    /**
     * method printOrderStatus: 주문정보 출력
     * @param allOrderInfo 출력할 주문정보 리스트
     */
    public static void printOrderStatus(List<MemberOrderDTO> allOrderInfo) {
        for (MemberOrderDTO memberOrderDTO : allOrderInfo) {
            System.out.println("==================");
            System.out.println("|| " + memberOrderDTO.getMemberId() + "번 고객님 ||");
            System.out.println("-----------------");
            System.out.println("|| 주문번호:" + memberOrderDTO.getMemberOrderId() + " ||");
            System.out.println("==================");
        }
    }

    /**
     * method printMenuInfo: 담아두기 정보 출력
     * @param cart 출력할 담아두기 정보 리스트
     */
    public static void printMenuInfo(ArrayList<MenuDTO> cart) {
        int totalPrice = 0;
        int menuCnt = 1;
        System.out.println("============== 장바구니 ==============");
        for (MenuDTO menuDTO : cart) {
            System.out.print(menuCnt+++": ");
            System.out.print(menuDTO.getMenuName());
            System.out.print(" / ");
            System.out.println(menuDTO.getMenuPrice()+"원");
            totalPrice += menuDTO.getMenuPrice();

        }
        System.out.println("총 가격: "+totalPrice+"원");
        System.out.println("====================================");
    }

    /**
     * method printMessageOrderSuccess: 주문성공 출력
     * @param message 출력 메세지
     */
    public static void printMessageOrderSuccess(String message) {
        System.out.println(message);

    }

    /**
     * method printMemberOrderDTO: 특정 고객의 과거 주문 기록 출력
     * @param history 기록 정보
     */
    public static void printMemberOrderDTO(HistoryVo history) {
        System.out.println(history);
    }
}