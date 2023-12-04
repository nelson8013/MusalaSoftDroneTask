package com.musalasoft.app.Models;

import com.musalasoft.app.Dtos.Requests.MedicationRequest;
import com.musalasoft.app.Exceptions.MedicationExceptions.CodeCaseInvalidException;
import com.musalasoft.app.Exceptions.MedicationExceptions.NameCaseInvalidException;
import jakarta.persistence.*;

import java.util.Objects;

import static com.musalasoft.app.Utils.RequirementValidation.isCodeValid;
import static com.musalasoft.app.Utils.RequirementValidation.isNameValid;

@Entity
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String code;
    private Double weight;
    private String image;


    public Medication(){}

    public Medication(String name, String code, Double weight,  String image){
        this.name   = name;
        this.code   = code;
        this.weight = weight;
        this.image  = image;
    }

    public Medication(MedicationRequest request) {
        this.name   = request.getName();
        this.code   = request.getCode();
        this.weight = request.getWeight();
        this.image  = request.getImage();
    }


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

    public String getName(){
        return this.name;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medication that = (Medication) o;
        return Double.compare(that.weight, weight) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(code, that.code) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, weight, image);
    }

}
