package com.CornFarmerNZ.PetDaycare.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long petId;
    @NotBlank(message = "Pet's name is required.")
    private String petName;
    Long petRating; //out of 5 stars.
    @NotBlank
    private Long ownerId;
    //Optional values:
    private double petAge;
    private String petBreed;
    private String favouriteFood;
    private String petLocation;


}
