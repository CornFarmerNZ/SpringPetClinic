package com.CornFarmerNZ.PetDaycare.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    Long petId;
    @Column(name = "NAME")
    private String petName;
    @Column(name = "RATING")
    Long petRating; //out of 5 stars.

    //Optional values:
    @Column(name = "AGE")
    private Long petAge;
    @Column(name = "BREED")
    private String petBreed;
    @Column(name = "LOCATION")
    private String petLocation;
    @Column(name = "PICTURE")
    private String petPicture;


}
