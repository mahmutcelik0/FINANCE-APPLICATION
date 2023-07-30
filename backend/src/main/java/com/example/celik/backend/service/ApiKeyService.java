package com.example.celik.backend.service;

import com.example.celik.backend.exception.ApiKeyNotFoundException;
import com.example.celik.backend.model.APIKey;
import com.example.celik.backend.repository.ApiKeyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiKeyService {
    private final ApiKeyRepository apiKeyRepository;

    public ApiKeyService(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    public APIKey getApiKeyByIntegrationName(String integrationClassName) throws ApiKeyNotFoundException {
        Optional<APIKey> key = apiKeyRepository.findAPIKeyByClassName(integrationClassName);

        if(key.isEmpty()){
            throw new ApiKeyNotFoundException("API KEY NOT FOUND");
        }

        return key.get();
    }
}
