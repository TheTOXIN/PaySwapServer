package com.toxin.payswap.repository;

import com.toxin.payswap.enity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TransactionRepository extends CrudRepository<Transaction, UUID> {

}
