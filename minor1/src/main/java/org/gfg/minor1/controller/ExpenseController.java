package org.gfg.minor1.controller;

import org.gfg.minor1.request.ExpenseTypeRequest;
import org.gfg.minor1.response.ExpenseTypeResponse;
import org.gfg.minor1.response.GenericResponse;
import org.gfg.minor1.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/expensetracker")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;


    @PostMapping("/addExpenseType")
    public GenericResponse<ExpenseTypeResponse> addExpenseType(@Valid @RequestBody ExpenseTypeRequest expenseTypeRequest) {
        ExpenseTypeResponse expenseTypeResponse = expenseService.addExpenseType(expenseTypeRequest);
        GenericResponse genericResponse = GenericResponse.builder().
                code(HttpStatus.OK.value()).
                statusCode(0).
                message("data has been saved to db").
                data(expenseTypeResponse).build();
        return genericResponse;

    }

    }
