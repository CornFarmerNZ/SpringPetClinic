package com.CornFarmerNZ.PetDaycare.service;


import com.CornFarmerNZ.PetDaycare.entity.Owner;
import com.CornFarmerNZ.PetDaycare.entity.Pet;

import java.util.List;

public interface PetDaycareService {

    public List<Pet> getAllPets();
    public Pet checkInPet(Pet pet);
    public Pet checkOutPet(Long id);
    public Owner addOwner(Owner owner);
    public Owner getOwnerById(Long id) throws Exception;

}
