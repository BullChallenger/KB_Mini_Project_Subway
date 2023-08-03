package dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberOrderDTO {

    private Long memberOrderId;

    private int selectBread;

    private int selectCheese;

    private String selectedAdditionalMenu;

    private String excludedVegetable;

    private String selectedSource;

    private String orderDate;

    private char orderStatus;

    private Long memberId;

    private Long menuId;
}
