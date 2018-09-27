package com.toxin.payswap.repository;

import com.toxin.payswap.enity.VirtualCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VirtcardRepository extends JpaRepository<VirtualCard, UUID> {

}
