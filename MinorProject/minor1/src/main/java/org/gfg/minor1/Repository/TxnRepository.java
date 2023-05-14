package org.gfg.minor1.Repository;

import org.gfg.minor1.model.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TxnRepository extends JpaRepository<TransactionDetails, Integer> {

    public List<TransactionDetails> findByExpenseDetailsIn(String[] values);

    public List<TransactionDetails> findByExpenditureCost(Double amount);

    public List<TransactionDetails> findAllByExpenditureCostLessThanEqual(Double amount);
    public List<TransactionDetails> findByExpenseDate(LocalDate expenseDate);
    public List<TransactionDetails> findAllByExpenseDateLessThanEqual(LocalDate expenseDate);

    @Query(value = "select SUM(t.expenditure_cost) from txn_details t inner join user u where t.user_id = u.id and t.expense_date >= :date and u.id = :id",nativeQuery = true)
    public Double getAggregatedData(LocalDate date, Integer id);


}
