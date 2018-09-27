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
@Table(name = "swaps")
@NoArgsConstructor
@AllArgsConstructor
public class Swap {

    @Id
    @GeneratedValue
    private UUID id;

    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private User creator;

    @OneToMany(mappedBy = "swap")
    private List<User> users = new ArrayList<>();

    @JoinColumn(name = "virtcard_id", referencedColumnName = "id")
    @OneToOne(optional = false)
    private VirtualCard virtualCard;

    private String description;

    private long point;

    private String hash;

}
