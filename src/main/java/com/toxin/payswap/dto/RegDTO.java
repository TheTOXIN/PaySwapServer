package com.toxin.payswap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegDTO implements Serializable {

    private String firstName;
    private String lastName;
    private CardDTO card;

}
