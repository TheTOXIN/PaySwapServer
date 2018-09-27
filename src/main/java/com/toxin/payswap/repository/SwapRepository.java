package com.toxin.payswap.repository;

import com.toxin.payswap.enity.Card;
import com.toxin.payswap.enity.Swap;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SwapRepository extends CrudRepository<Swap, UUID> {

    Swap findByHash(UUID hash);

}
