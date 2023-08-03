package dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {

    private Long ingredientId;

    private String ingredientName;

    private int stock;

    private int ingredientPrice;

    private int ingredientCalorie;

    private int ingredientCategory;
}
