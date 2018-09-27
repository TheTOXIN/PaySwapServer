package com.toxin.payswap.controller;

import com.toxin.payswap.dto.PayDTO;
import com.toxin.payswap.service.SwapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(name = "cards")
public class CardController {

    @Autowired
    private SwapService swapService;

    @PostMapping("pay")
    public boolean pay(@RequestBody PayDTO payDTO) {
        return swapService.pay(payDTO);
    }

}
