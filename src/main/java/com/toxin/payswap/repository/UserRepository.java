package com.toxin.payswap.repository;

import com.toxin.payswap.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
