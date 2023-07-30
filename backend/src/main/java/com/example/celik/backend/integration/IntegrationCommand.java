package com.example.celik.backend.integration;

import com.example.celik.backend.integration.response.ExchangeIntegrationResponse;
import com.example.celik.backend.model.APIKey;

public abstract class IntegrationCommand {
    public abstract ExchangeIntegrationResponse makeRequest();

    abstract APIKey getApiKeyFromDatabase(String className);

}
