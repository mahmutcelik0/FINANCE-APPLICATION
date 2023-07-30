package com.example.celik.backend.controller;

import com.example.celik.backend.integration.ExchangeIntegrationCommand;
import com.example.celik.backend.integration.IntegrationCommand;
import com.example.celik.backend.integration.response.ExchangeIntegrationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integration")
public class IntegrationController {
    private final IntegrationCommand command;

    public IntegrationController(ExchangeIntegrationCommand command) {
        this.command = command;
    }

    @GetMapping
    public String getResponse(){
        return command.makeRequest();
    }




}
