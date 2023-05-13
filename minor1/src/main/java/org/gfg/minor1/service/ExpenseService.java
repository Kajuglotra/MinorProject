package org.gfg.minor1.service;

import org.gfg.minor1.Repository.ExpenseRepository;
import org.gfg.minor1.Repository.UserRepository;
import org.gfg.minor1.model.ExpenseDetails;
import org.gfg.minor1.model.User;
import org.gfg.minor1.request.ExpenseTypeRequest;
import org.gfg.minor1.response.ExpenseTypeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository ;

    @Autowired
    private UserRepository userRepository;

    public ExpenseTypeResponse addExpenseType(ExpenseTypeRequest expenseTypeRequest) {
        ExpenseDetails expenseDetails = expenseTypeRequest.toExpenseDetails();
        expenseRepository.save(expenseDetails);
        User user = userRepository.getUserByMailId(expenseDetails.getCreatedBy());
        ExpenseTypeResponse expenseTypeResponse = ExpenseTypeResponse.builder().
                expenseType(expenseDetails.getExpenseType()).
                userId(user.getId()).
                build();
        return expenseTypeResponse;
    }
}
