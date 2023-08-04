package vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HistoryVo {
    private String breadName;
    private String cheeseName;
    private String additionalMenu;
    private String excludeVege;
    private String selectedSource;
}
