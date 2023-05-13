package org.gfg.minor1.controller;

import org.gfg.minor1.request.CreateTxnRequest;
import org.gfg.minor1.response.CreateTxnResponse;
import org.gfg.minor1.response.GenericResponse;
import org.gfg.minor1.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

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

}
