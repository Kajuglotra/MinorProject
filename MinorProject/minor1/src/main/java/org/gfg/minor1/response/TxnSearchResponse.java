package org.gfg.minor1.response;

import lombok.*;
import org.gfg.minor1.model.User;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TxnSearchResponse {
    User user;
    Double expenditureCost;
    String expenseType;
    String expenseDate;
}
