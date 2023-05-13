package org.gfg.minor1.service;

import org.gfg.minor1.Repository.ExpenseRepository;
import org.gfg.minor1.Repository.TxnRepository;
import org.gfg.minor1.Repository.UserRepository;
import org.gfg.minor1.model.ExpenseDetails;
import org.gfg.minor1.model.TransactionDetails;
import org.gfg.minor1.model.User;
import org.gfg.minor1.model.UserState;
import org.gfg.minor1.request.CreateTxnRequest;
import org.gfg.minor1.response.CreateTxnResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TxnService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private TxnRepository txnRepository;
    public CreateTxnResponse addUserTxn(CreateTxnRequest createTxnRequest) {
        User userFromDb = userRepository.getUserByMailId(createTxnRequest.getUserEmail());

            if(userFromDb == null){
                User user = User.builder().
                        mail(createTxnRequest.getUserEmail()).
                        status(UserState.ACTIVE).
                        build();
                userRepository.save(user);
            }
        userFromDb = userRepository.getUserByMailId(createTxnRequest.getUserEmail());
        ExpenseDetails expenseDetailsFromDB = expenseRepository.findByExpenseType(createTxnRequest.getExpenseType());
            if(expenseDetailsFromDB == null){
                ExpenseDetails expenseDetails = ExpenseDetails.builder().
                        expenseType(createTxnRequest.getExpenseType()).
                        createdBy(userFromDb.getMail()).build();
                expenseRepository.save(expenseDetails);
            }
         expenseDetailsFromDB = expenseRepository.findByExpenseType(createTxnRequest.getExpenseType());
         TransactionDetails transactionDetails = createTxnRequest.toTxnDetails();
         transactionDetails.setUser(userFromDb);
         transactionDetails.setExpenseDetails(expenseDetailsFromDB);
         TransactionDetails transactionDetails1 = txnRepository.save(transactionDetails);

        return  CreateTxnResponse.builder().
                userId(transactionDetails1.getUser().getId()).
                expenseId(transactionDetails1.getId()).
                build();
    }
}
