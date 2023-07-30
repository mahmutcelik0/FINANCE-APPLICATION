package com.example.celik.backend.integration.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeIntegrationRequest implements IntegrationRequest{
    private final String baseURI = "https://v6.exchangerate-api.com/v6/%s/latest/USD"; //https://v6.exchangerate-api.com/v6/YOUR-API-KEY/latest/USD
    private String apiKey;

    public String getRequest(){
        return String.format(baseURI,apiKey);
    }

}


