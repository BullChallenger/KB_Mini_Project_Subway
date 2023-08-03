package dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long memberId;

    private String memberName;

    private String phoneNumber;

    private int point;

    private List<MemberOrderDTO> memberOrders = new ArrayList<>();
}
