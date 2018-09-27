package com.toxin.payswap.controller;

import com.toxin.payswap.dto.SwapDTO;
import com.toxin.payswap.dto.TransactionDTO;
import com.toxin.payswap.dto.VirtCardDTO;
import com.toxin.payswap.service.SwapService;
import com.toxin.payswap.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("trans")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("add")
    public VirtCardDTO add(
        @RequestBody TransactionDTO dto
    ) {
        return transactionService.add(dto);
    }

}
