package dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AnonymousOrderDTO {

    private Long anonymousOrderId;

    private String orderDate;

    private Long menuId;
}
