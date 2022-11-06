package com.example.carrental.controller;

import com.example.carrental.model.*;
import com.example.carrental.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contract")
public class ContractController {
    @Autowired
    private IContractService iContractService;

    @PostMapping("/saveContract")
    ResponseEntity<ResponseObject> saveContract(@RequestBody Contract contract) {
        iContractService.save(contract);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Save successfully", "")
        );
    }
}
