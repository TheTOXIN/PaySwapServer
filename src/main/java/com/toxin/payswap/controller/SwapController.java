package com.toxin.payswap.controller;

import com.toxin.payswap.dto.SwapDTO;
import com.toxin.payswap.service.SwapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("swaps")
public class SwapController {

    @Autowired
    private SwapService swapService;

    @PostMapping("create")
    public String create(@RequestBody SwapDTO swapDTO) {
        return swapService.create(swapDTO);
    }

}
