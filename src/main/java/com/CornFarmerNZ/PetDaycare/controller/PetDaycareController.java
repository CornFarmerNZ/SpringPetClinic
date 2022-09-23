package com.CornFarmerNZ.PetDaycare.controller;

import com.CornFarmerNZ.PetDaycare.entity.Owner;
import com.CornFarmerNZ.PetDaycare.entity.Pet;
import com.CornFarmerNZ.PetDaycare.service.PetDaycareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PetDaycareController {

    @Autowired
    private PetDaycareService service;

//    @PostMapping("/")
//    public Pet checkInPet(@RequestBody @Valid Pet pet){
//        return service.checkInPet(pet);
//    }
//
//    @GetMapping("/{id}")
//    public Pet checkOutPet(@PathVariable("id") Long id){
//        return service.checkOutPet(id);
//    }
//
//    @GetMapping("/")
//    public List<Pet> getAllPets(){
//        return service.getAllPets();
//    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("allPets", service.getAllPets());
        return "home";
    }

    @GetMapping("/addPet")
    public String addOwner(Model model){
        return "addPet";
    }

    @PostMapping("/CheckInPet")
    public Pet addPet(@RequestBody Pet pet, Long ownerId) throws Exception {
        Long verifiedId = service.getOwnerById(ownerId).getId();
        return service.checkInPet(pet);
    }





}
