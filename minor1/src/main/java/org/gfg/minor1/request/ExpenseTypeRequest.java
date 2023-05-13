package org.gfg.minor1.request;

import lombok.*;
import org.gfg.minor1.model.ExpenseDetails;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseTypeRequest {
    @NotBlank
    String expenseType;
    @NotBlank
    String email;

    public ExpenseDetails toExpenseDetails() {
        return ExpenseDetails.builder().
                expenseType(this.expenseType).
                createdBy(email).build();
    }
}
