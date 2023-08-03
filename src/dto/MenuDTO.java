package dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MenuDTO {

    private Long menuId;

    private String menuName;

    private int menuPrice;

    private int menuCalorie;

    private List<ComposeDTO> ingredients = new ArrayList<>();

    public MenuDTO(Long menuId, String menuName, int menuPrice, int menuCalorie) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuCalorie = menuCalorie;
    }
}
