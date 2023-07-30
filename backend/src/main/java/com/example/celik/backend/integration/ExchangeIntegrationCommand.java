package com.example.celik.backend.integration;

import com.example.celik.backend.exception.ApiKeyNotFoundException;
import com.example.celik.backend.integration.request.ExchangeIntegrationRequest;
import com.example.celik.backend.integration.request.IntegrationRequest;
import com.example.celik.backend.integration.response.ExchangeIntegrationResponse;
import com.example.celik.backend.integration.response.IntegrationResponse;
import com.example.celik.backend.model.APIKey;
import com.example.celik.backend.service.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Component
public class ExchangeIntegrationCommand extends IntegrationCommand{
    private final ApiKeyService apiKeyService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    public ExchangeIntegrationCommand(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @Override
    public ExchangeIntegrationResponse makeRequest() {
        APIKey key = getApiKeyFromDatabase(getClass().getSimpleName());

        ExchangeIntegrationRequest request = new ExchangeIntegrationRequest(key.getKeyName());

        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Object> response = restTemplate.exchange(request.getRequest(), HttpMethod.GET,entity, Object.class);

        HashMap<String,Object> map = (HashMap<String, Object>) response.getBody();
        return ExchangeIntegrationResponse.convert(map);
    }

    @Override
    APIKey getApiKeyFromDatabase(String className) {
        try {
            return apiKeyService.getApiKeyByIntegrationName(className);
        } catch (ApiKeyNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
