package vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderVo {

    private int menuId;

    private int selectBread;

    private int selectCheese;

    private String selectedAdditionalMenu;

    private String excludedVegetable;

    private String selectedSource;

}
