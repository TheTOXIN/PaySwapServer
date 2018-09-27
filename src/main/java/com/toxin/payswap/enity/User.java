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
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @JoinColumn(name = "card_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Card card;

    @JoinColumn(name = "swap_id", referencedColumnName = "id")
    @ManyToOne
    private Swap swap;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions = new ArrayList<>();

}
