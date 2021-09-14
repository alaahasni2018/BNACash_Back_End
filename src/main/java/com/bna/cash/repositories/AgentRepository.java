package com.bna.cash.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bna.cash.entities.Agent;
import com.bna.cash.entities.User;

public interface AgentRepository extends JpaRepository<Agent, Long> {

	Agent findAgentById(Long id); 
}
