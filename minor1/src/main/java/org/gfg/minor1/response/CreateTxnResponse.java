package org.gfg.minor1.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTxnResponse {

    private Integer userId;
    private Integer expenseId;

}
