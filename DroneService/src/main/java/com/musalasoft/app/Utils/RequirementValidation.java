package com.musalasoft.app.Utils;

import com.musalasoft.app.Exceptions.DroneExceptions.DroneStateException;
import com.musalasoft.app.Models.DroneState;

import java.util.Arrays;

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

    public static boolean isCodeValid(String code) {
        String allowedCases = "^[A-Z0-9_]+$";

        return code.matches(allowedCases);
    }

    public static boolean isNameValid(String name){
        String allowedCases = "^[a-zA-Z0-9_-]+$";
        return name.matches(allowedCases);
    }

    public static boolean isDroneStateValid(String state) {
        DroneState.valueOf(state.toUpperCase());
        return true;
    }

    public static boolean isDroneSerialNumberCharsLessThanOneHundred(String serialNumber) {
        return serialNumber.length() < 100;
    }


}
