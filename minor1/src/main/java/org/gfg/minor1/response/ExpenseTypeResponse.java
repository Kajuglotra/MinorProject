package org.gfg.minor1.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseTypeResponse {
    private Integer userId;
    private String expenseType;
}
