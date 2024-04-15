package com.express.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ThripatiteExpressEnum {

    STO(1, "申通", "sto");


    private final Integer id;

    private final String chineseName;

    private final String englishName;

    public static ThripatiteExpressEnum getEnglishName(String englishName) {

        for (ThripatiteExpressEnum thripatiteExpressEnum : ThripatiteExpressEnum.values()) {
            if (thripatiteExpressEnum.getEnglishName().equals(englishName)) {
                return thripatiteExpressEnum;
            }
        }
        return null;
    }


}
