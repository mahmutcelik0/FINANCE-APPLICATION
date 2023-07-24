package com.example.celik.backend.repository;

import com.example.celik.backend.model.Wealth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WealthRepository extends JpaRepository<Wealth,Long> {
    boolean existsWealthByNameContainingIgnoreCaseAndLongNameContainingIgnoreCase(String name,String longName);

    Optional<Wealth> findByName(String name);
}
