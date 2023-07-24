package com.example.celik.backend.service;

import com.example.celik.backend.dto.WealthDto;
import com.example.celik.backend.exception.WealthNotFoundException;
import com.example.celik.backend.model.Wealth;
import com.example.celik.backend.repository.WealthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WealthService {
    private final WealthRepository wealthRepository;

    private final Logger logger = LoggerFactory.getLogger(WealthService.class);

    public WealthService(WealthRepository wealthRepository) {
        this.wealthRepository = wealthRepository;
    }

    public List<WealthDto> getWealths() throws WealthNotFoundException {
        List<Wealth> wealths = wealthRepository.findAll();

        if (wealths.isEmpty()) {
            logger.warn("There isn't any wealth found");
            throw new WealthNotFoundException("There isn't any wealth found");
        }

        return wealths
                .stream()
                .map(WealthDto::perform)
                .collect(Collectors.toList());

    }

    public ResponseEntity addNewWealth(Wealth wealth) {
        boolean isExist = wealthRepository.existsWealthByNameContainingIgnoreCaseAndLongNameContainingIgnoreCase(wealth.getName(), wealth.getLongName());

        if (isExist) {
            logger.warn("Wealth already exists");
            return new ResponseEntity("Wealth already exists", HttpStatus.CONFLICT);
        }

        wealthRepository.save(wealth);

        return new ResponseEntity("Wealth saved successfully", HttpStatus.OK);
    }

    public ResponseEntity updateWealthbyName(Wealth newWealth, String name) {
        Optional<Wealth> wealth = wealthRepository.findByName(name);

        if(wealth.isEmpty()){
            logger.warn("Wealth does not exist");
            return new ResponseEntity("Wealth does not exist",HttpStatus.NOT_FOUND);
        }

        wealthRepository.delete(wealth.get());
        wealthRepository.save(newWealth);

        return new ResponseEntity("Wealth updated successfully",HttpStatus.OK);
    }

    public ResponseEntity deleteWealthByName(String name) {
        Optional<Wealth> wealth = wealthRepository.findByName(name);

        if(wealth.isEmpty()){
            logger.warn("Wealth does not exist");
            return new ResponseEntity("Wealth does not exist",HttpStatus.NOT_FOUND);
        }

        wealthRepository.delete(wealth.get());

        return new ResponseEntity("Wealth deleted successfully",HttpStatus.OK);
    }
}