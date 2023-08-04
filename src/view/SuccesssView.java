package view;

import dto.IngredientDTO;
import dto.MemberOrderDTO;
import dto.MenuDTO;
import vo.HistoryVo;

import java.util.ArrayList;
import java.util.List;

public class SuccesssView {

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

    public static void printStocks(List<IngredientDTO> ingredientDTOS) {
        for (IngredientDTO ingredientDTO : ingredientDTOS) {
            System.out.println(ingredientDTO.getIngredientId()+"|"+ingredientDTO.getIngredientName()+"|"+ingredientDTO.getStock()
                    +"|"+ingredientDTO.getIngredientCategory());
        }
    }

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

    public static void printOrderStatus(List<MemberOrderDTO> allOrderInfo) {
        for (MemberOrderDTO memberOrderDTO : allOrderInfo) {
            System.out.println("==================");
            System.out.println("|| " + memberOrderDTO.getMemberId() + "번 고객님 ||");
            System.out.println("-----------------");
            System.out.println("|| 주문번호:" + memberOrderDTO.getMemberOrderId() + " ||");
            System.out.println("==================");
        }
    }

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

    public static void printMessageOrderSuccess(String message) {
        System.out.println(message);

    }

    public static void printMemberOrderDTO(HistoryVo history) {
        System.out.println(history);
    }
}