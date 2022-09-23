package com.CornFarmerNZ.PetDaycare.service;

import com.CornFarmerNZ.PetDaycare.entity.Owner;
import com.CornFarmerNZ.PetDaycare.entity.Pet;
import com.CornFarmerNZ.PetDaycare.repository.OwnerRepository;
import com.CornFarmerNZ.PetDaycare.repository.PetDaycareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetDaycareServiceImplementation implements PetDaycareService{

    @Autowired
    private PetDaycareRepository repository;
    @Autowired
    private OwnerRepository ownerRepository;


    @Override
    public Pet checkInPet(Pet pet) {
        repository.save(pet);
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
    public Owner addOwner(Owner owner) {
        ownerRepository.save(owner);
        return owner;
    }

    @Override
    public Owner getOwnerById(Long id) throws Exception {
        if(ownerRepository.findById(id).isPresent()){
        return ownerRepository.findById(id).get();
        }
        else throw new Exception("Id("+id+") not found.");
    }


    @Override
    public List<Pet> getAllPets() {
        return repository.findAll();
    }
}
