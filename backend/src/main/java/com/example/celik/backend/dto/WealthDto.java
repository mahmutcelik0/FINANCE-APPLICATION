package com.example.celik.backend.dto;

import com.example.celik.backend.constants.UNIT;
import com.example.celik.backend.model.Wealth;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WealthDto{
    private String name;
    private String longName;
    private String unit;

    public WealthDto(String name, String longName, UNIT unit) {
        this.name = name;
        this.longName = longName;
        this.unit = unit.getUnit();
    }

    public static WealthDto perform(Wealth wealth) {
        try {
            return new WealthDto(wealth.getName(),wealth.getLongName(),wealth.getUnit());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", longName='" + longName + '\'' +
                ", unit=" + unit +
                '}';
    }
}
