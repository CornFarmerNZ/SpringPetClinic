package com.CornFarmerNZ.PetDaycare.service;

import com.CornFarmerNZ.PetDaycare.entity.Pet;
import com.CornFarmerNZ.PetDaycare.repository.PetDaycareRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PetDaycareServiceImplementation implements PetDaycareService{

    @Autowired
    private PetDaycareRepository repository;

    @Override
    public Pet checkInPet(Pet pet) {
        Pet petToSave =
                Pet.builder().petAge(pet.getPetAge()).petLocation(pet.getPetLocation()).petName(pet.getPetName()).petBreed(pet.getPetBreed()).petId(pet.getPetId()).petRating((long) 1.0d).petPicture(pet.getPetPicture()).build();
        log.info("Pet to save: "+pet.toString());
        repository.save(petToSave);
        return pet;
    }

    @Override
    public Pet checkOutPet(Long id) {
        Optional<Pet> petToCheckout = repository.findById(id);
        if(petToCheckout.isPresent()) {
            repository.deleteById(id);
            return petToCheckout.get();
        }
        else{
            System.out.println("Error: pet id doesn't exist.");
            return null;
        }
    }



    @Override
    public List<Pet> getAllPets() {
        return repository.findAll();
    }
}
