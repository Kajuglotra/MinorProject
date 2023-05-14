package org.gfg.minor1.controller;

import org.gfg.minor1.model.TxnFilterOperators;
import org.gfg.minor1.model.TxnFilterType;
import org.gfg.minor1.request.CreateTxnRequest;
import org.gfg.minor1.response.AnalyticalResponse;
import org.gfg.minor1.response.CreateTxnResponse;
import org.gfg.minor1.response.GenericResponse;
import org.gfg.minor1.response.TxnSearchResponse;
import org.gfg.minor1.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/expenseTracker")
public class TxnController {
    @Autowired
    private TxnService txnService;

    @PostMapping("/addUserTxn")
    public GenericResponse<CreateTxnResponse> addUserTxn(@Valid @RequestBody CreateTxnRequest createTxnRequest){
        CreateTxnResponse createTxnResponse = txnService.addUserTxn(createTxnRequest);
        GenericResponse genericResponse = GenericResponse.builder().
                code(HttpStatus.OK.value()).
                statusCode(0).
                message("data has been saved to db").
                data(createTxnResponse).build();
        return genericResponse;
    }

    @GetMapping("/fetchTxn")
    public GenericResponse<List<TxnSearchResponse>> fetchUserTxn(@RequestParam("filter")TxnFilterType txnFilterType,
                                                           @RequestParam("operator")TxnFilterOperators txnFilterOperators,
                                                           @RequestParam("value") String value) throws ParseException {
        String[] valArr = value.split(",");
        List<TxnSearchResponse> txnSearchResponse = txnService.fetchUserTxn(txnFilterType, txnFilterOperators, valArr);
        GenericResponse genericResponse = GenericResponse.builder().
                code(HttpStatus.OK.value()).
                statusCode(0).
                message("data has been saved to db").
                data(txnSearchResponse).build();
        return genericResponse;
    }

    @GetMapping("/fetchCalculatedResults")
    public GenericResponse<AnalyticalResponse> fetchCalculatedResults(@RequestParam("email") String email) throws ParseException {
        AnalyticalResponse analyticalResponse =txnService.fetchCalculatedResults(email);
        GenericResponse genericResponse = GenericResponse.builder().
                code(HttpStatus.OK.value()).
                statusCode(0).
                message("data has been saved to db").
                data(analyticalResponse).build();
        return genericResponse;


    }


}
