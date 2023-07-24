package com.example.celik.backend.controller;

import com.example.celik.backend.dto.WealthDto;
import com.example.celik.backend.exception.WealthNotFoundException;
import com.example.celik.backend.model.Wealth;
import com.example.celik.backend.service.WealthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wealth")
public class WealthController {
    private final WealthService wealthService;

    public WealthController(WealthService wealthService) {
        this.wealthService = wealthService;
    }

    @GetMapping
    public List<WealthDto> getWealths() throws WealthNotFoundException {
        return wealthService.getWealths();
    }

    @PostMapping
    public ResponseEntity addNewWealth(@RequestBody Wealth wealth){
        return wealthService.addNewWealth(wealth);
    }

    @PutMapping
    public ResponseEntity updateWealthbyName(@RequestBody Wealth newWealth,@RequestParam String name){
        return wealthService.updateWealthbyName(newWealth,name);
    }

    @DeleteMapping
    public ResponseEntity deleteWealthByName(@RequestParam String name){
        return wealthService.deleteWealthByName(name);
    }





}
