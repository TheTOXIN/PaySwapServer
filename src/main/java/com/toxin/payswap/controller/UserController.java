package com.toxin.payswap.controller;

import com.toxin.payswap.dto.RegDTO;
import com.toxin.payswap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("reg")
    public UUID reg(@RequestBody RegDTO regDTO) {
        return userService.reg(regDTO);
    }

}
