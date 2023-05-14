package org.gfg.minor1.service;

import org.gfg.minor1.Repository.ExpenseRepository;
import org.gfg.minor1.Repository.TxnRepository;
import org.gfg.minor1.Repository.UserRepository;
import org.gfg.minor1.model.*;
import org.gfg.minor1.request.CreateTxnRequest;
import org.gfg.minor1.response.AnalyticalResponse;
import org.gfg.minor1.response.CreateTxnResponse;
import org.gfg.minor1.response.TxnSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.MediaSize;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

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


    public List<TxnSearchResponse> fetchUserTxn(TxnFilterType txnFilterType, TxnFilterOperators txnFilterOperators, String[] values) throws ParseException {
        List<TransactionDetails> list = new ArrayList<>();
       switch (txnFilterType){
           case EXPENSE_TYPE:
               list.addAll(txnRepository.findByExpenseDetailsIn(values));
               break;
           case EXPENDITURE_AMOUNT:
               switch (txnFilterOperators){
                   case EQUALS:
                       list.addAll(txnRepository.findByExpenditureCost(Double.valueOf(values[0])));
                       break;
                   case LESS_THAN_EQUALS:
                       list.addAll(txnRepository.findAllByExpenditureCostLessThanEqual(Double.valueOf(values[0])));
                       break;
               }
               break;
           case EXPENSE_DATE:
               switch (txnFilterOperators){
                   case EQUALS:
                       list.addAll(txnRepository.findByExpenseDate(LocalDate.parse(values[0])));
                       break;
                   case LESS_THAN_EQUALS:
                       list.addAll(txnRepository.findAllByExpenseDateLessThanEqual(LocalDate.parse(values[0])));
                       break;
               }
               break;
       }
       List<TxnSearchResponse> txnSearchResponses = convertToSearchResponse(list);
       return txnSearchResponses;

    }
    public List<TxnSearchResponse> convertToSearchResponse(List<TransactionDetails> list){
        List<TxnSearchResponse> list1 = new ArrayList<>();

        for(int i = 0 ; i<list.size();i++){
            TxnSearchResponse txnSearchResponse = TxnSearchResponse.builder().
                    user(list.get(i).getUser()).
                    expenditureCost(list.get(i).getExpenditureCost()).
                    expenseDate(list.get(i).getExpenseDate().toString()).
                    expenseType(list.get(i).getExpenseDetails().getExpenseType()).
                    build();
            list1.add(txnSearchResponse);
        }
        return list1;

    }

    public AnalyticalResponse fetchCalculatedResults(String email) throws ParseException {
        User user = userRepository.findByMail(email);
        LocalDate todayMidnight = LocalDate.now();

        LocalDate sevenDaysBackMidnight = LocalDate.now().minusDays(7);


        Double oneDayAmount = txnRepository.getAggregatedData(todayMidnight, user.getId());
        Double sevenDayAmount = txnRepository.getAggregatedData(sevenDaysBackMidnight, user.getId());

        return AnalyticalResponse.builder().
                userEmail(email).
                oneDayAmount(oneDayAmount).
                sevenDayAmount(sevenDayAmount).build();
    }
}
