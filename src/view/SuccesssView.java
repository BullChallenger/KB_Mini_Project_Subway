package view;

import dto.IngredientDTO;
import dto.MemberOrderDTO;
import dto.MenuDTO;

import java.util.ArrayList;
import java.util.List;

public class SuccesssView {

    public static void printSelect(List<IngredientDTO> ingredientDTOS) {
        for (IngredientDTO ingredientDTO : ingredientDTOS) {
            System.out.println(ingredientDTO.getIngredientName());
        }
    }

    public static void printStocks(List<IngredientDTO> ingredientDTOS) {
        for (IngredientDTO ingredientDTO : ingredientDTOS) {
            System.out.println(ingredientDTO.getIngredientId()+"|"+ingredientDTO.getIngredientName()+"|"+ingredientDTO.getStock()
                    +"|"+ingredientDTO.getIngredientCategory());
        }
    }

    public static void printAllMenu(List<MenuDTO> allMenu) {
        for (MenuDTO menu : allMenu) {
            System.out.println(menu.getMenuName());
        }
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
}