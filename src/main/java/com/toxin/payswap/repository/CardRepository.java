package com.toxin.payswap.repository;

import com.toxin.payswap.enity.Card;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CardRepository extends CrudRepository<Card, UUID> {

}
