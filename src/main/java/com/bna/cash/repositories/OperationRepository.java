package com.bna.cash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bna.cash.entities.Operation;

public interface OperationRepository  extends JpaRepository<Operation, Long>{

}
