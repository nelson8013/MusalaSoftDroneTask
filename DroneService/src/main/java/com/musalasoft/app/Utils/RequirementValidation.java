package com.musalasoft.app.Utils;

public class RequirementValidation {

    public static boolean isModelValid(String model) {
        return  model.equals("Lightweight") ||
                model.equals("Middleweight") ||
                model.equals("Cruiserweight") ||
                model.equals("Heavyweight");
    }

    public static boolean isSerialNumberValid(String serialNumber) {
        return serialNumber.length() <= 100;
    }

    public static boolean isWeightLimitValid(Double weightLimit) {
        return weightLimit < 0 || weightLimit > 500;
    }

}
