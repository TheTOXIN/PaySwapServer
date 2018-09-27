package com.toxin.payswap.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "virtcards")
@NoArgsConstructor
@AllArgsConstructor
public class VirtualCard {

    @Id
    @GeneratedValue
    private UUID id;

    private long bill = 0L;

    @OneToMany(mappedBy = "virtcard")
    private List<Transaction> trans = new ArrayList<>();

}
