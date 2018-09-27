package com.toxin.payswap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwapDTO implements Serializable {

    private UUID userId;

    private long point;

    private String description;

    private long count;

}
