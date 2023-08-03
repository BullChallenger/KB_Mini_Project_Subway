package view;

import dto.IngredientDTO;
import dto.MenuDTO;

import java.util.List;

public class SuccesssView {

    public static void printSelect(List<IngredientDTO> ingredientDTOS) {
        for (IngredientDTO ingredientDTO : ingredientDTOS) {
            System.out.println(ingredientDTO.getIngredientName());
        }
    }

    public static void printAllMenu(List<MenuDTO> allMenu) {
        for (MenuDTO menu : allMenu) {
            System.out.println(menu.getMenuName());
        }
    }
}