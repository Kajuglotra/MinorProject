package org.gfg.minor1.Repository;

import org.gfg.minor1.model.ExpenseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseDetails, Integer> {

    public ExpenseDetails findByExpenseType(String type);
}
