package com.example.celik.backend.integration.response;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class ExchangeIntegrationResponse implements IntegrationResponse{
    private String result;
    private HashMap<String,Number> conversation_rates;

    public static ExchangeIntegrationResponse convert(Map<String,Object> map){
        return new ExchangeIntegrationResponse((String) map.get("result"), (HashMap<String, Number>) map.get("conversion_rates"));
    }

    public static JSONParser convertJson(){
        // TODO: 7/31/2023 add convert process of response class
    }
}
