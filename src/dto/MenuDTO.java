package dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {

    private Long menuId;

    private String menuName;

    private int menuPrice;

    private int menuCalorie;

    private List<ComposeDTO> ingredients = new ArrayList<>();
}
