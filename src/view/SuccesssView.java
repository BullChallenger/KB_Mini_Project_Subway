package view;

import dto.IngredientDTO;
import dto.MemberOrderDTO;
import dto.MenuDTO;

import java.util.ArrayList;
import java.util.List;

public class SuccesssView {

    public static void printSelect(List<IngredientDTO> ingredientDTOS) {
        int cnt = 1;
        for (int i = 0; i < ingredientDTOS.size(); ++i) {
            System.out.print("\t"+(cnt++)+": "+ingredientDTOS.get(i).getIngredientName()+"\t|");
        }
        System.out.println();
    }

    public static void printAllMenu(List<MenuDTO> allMenu) {
        int cnt = 1;
        for (int i = 0; i < allMenu.size(); ++i) {
            System.out.print("\t"+(cnt++)+": "+allMenu.get(i).getMenuName()+"\t|");
        }
        System.out.println();
    }

    public static void printOrderStatus(List<MemberOrderDTO> allOrderInfo) {
        for (MemberOrderDTO memberOrderDTO : allOrderInfo) {
            System.out.println(memberOrderDTO.getMemberId());
            System.out.println(memberOrderDTO.getMemberOrderId());
        }
    }

    public static void printMenuInfo(ArrayList<MenuDTO> cart) {
        for (MenuDTO menuDTO : cart) {
            System.out.println("menuDTO.getMenuName() = " + menuDTO.getMenuName());
        }

    }

    public static void printMessageOrderSuccess(String message) {
        System.out.println(message);

    }
}