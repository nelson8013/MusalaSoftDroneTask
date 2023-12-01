package com.musalasoft.app.Models;

import com.musalasoft.app.Exceptions.MedicationExceptions.CodeCaseInvalidException;
import com.musalasoft.app.Exceptions.MedicationExceptions.NameCaseInvalidException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static com.musalasoft.app.Utils.RequirementValidation.isCodeValid;
import static com.musalasoft.app.Utils.RequirementValidation.isNameValid;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Double weight;
    private String code;
    private String image;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setName(String name){
        if(!isNameValid(name)){
            throw new NameCaseInvalidException("Only letters, numbers, hyphens ('-'), and underscores ('_') are allowed");
        }
        this.name = name;
    }


    public void setWeight(Double weight){
        this.weight = weight;
    }
    public Double getWeight(){
        return this.weight;
    }


    public void setCode(String code){
        if(!isCodeValid(code)){
            throw new CodeCaseInvalidException("Only upper case letters, underscores and numbers are allowed");
        }
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return this.image;
    }


}
