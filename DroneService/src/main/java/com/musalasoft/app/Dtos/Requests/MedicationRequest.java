package com.musalasoft.app.Dtos.Requests;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationRequest {

    private String name;
    private String code;
    private Double weight;
    private String image;
}
