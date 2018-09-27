package com.toxin.payswap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VirtCardDTO implements Serializable {

    private long bill;
    private UUID id;

}
