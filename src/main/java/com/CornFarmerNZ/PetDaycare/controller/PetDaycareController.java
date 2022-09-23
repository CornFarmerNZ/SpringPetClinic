package com.CornFarmerNZ.PetDaycare.controller;

import com.CornFarmerNZ.PetDaycare.service.PetDaycareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PetDaycareController {

    @Autowired
    private PetDaycareService service;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("allPets", service.getAllPets());
        return "home";
    }

    @GetMapping("/addPet")
    public String addPetPage(Model model){
        return "addPet";
    }







}
