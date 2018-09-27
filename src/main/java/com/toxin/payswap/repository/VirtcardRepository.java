package com.toxin.payswap.repository;

import com.toxin.payswap.enity.VirtualCard;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface VirtcardRepository extends CrudRepository<VirtualCard, UUID> {



}
