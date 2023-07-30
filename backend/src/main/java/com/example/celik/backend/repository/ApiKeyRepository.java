package com.example.celik.backend.repository;

import com.example.celik.backend.model.APIKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiKeyRepository extends JpaRepository<APIKey,Long> {
    Optional<APIKey> findAPIKeyByClassName(String className);
}
