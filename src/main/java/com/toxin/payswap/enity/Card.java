package com.toxin.payswap.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "cards")
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private int code;

    @Column(nullable = false)
    private String number;

    private long money;

}
