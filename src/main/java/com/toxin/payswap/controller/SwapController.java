package com.toxin.payswap.controller;

import com.toxin.payswap.dto.RegDTO;
import com.toxin.payswap.dto.SwapDTO;
import com.toxin.payswap.service.SwapService;
import com.toxin.payswap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("swaps")
public class SwapController {

    @Autowired
    private SwapService swapService;

    @PostMapping("create")
    public UUID create(
        @RequestBody SwapDTO swapDTO
    ) {
        return swapService.create(swapDTO);
    }

}
