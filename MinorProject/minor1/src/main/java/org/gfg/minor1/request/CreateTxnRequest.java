package org.gfg.minor1.request;

import lombok.*;
import org.gfg.minor1.model.ExpenseDetails;
import org.gfg.minor1.model.TransactionDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTxnRequest {

    @NotNull(message = "user email can't be null")
    private String userEmail;

    @NotNull(message = "expense type can't be null")
    private String expenseType;

    @Positive(message = "expenditure cost can't be negative or zero")
    private Double expenditureCost;

    @NotBlank(message = "expense Date can't be empty")
    private String expenseDate;

    private String expenseNote;


    public TransactionDetails toTxnDetails() {
        return TransactionDetails.builder().
                expenditureCost(this.expenditureCost).expenseDate(LocalDate.parse(this.expenseDate)).build();

    }
}
