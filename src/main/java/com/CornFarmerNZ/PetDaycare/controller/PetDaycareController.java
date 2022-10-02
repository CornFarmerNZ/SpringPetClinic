package com.CornFarmerNZ.PetDaycare.controller;

import com.CornFarmerNZ.PetDaycare.service.PetDaycareService;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class PetDaycareController {

    @Autowired
    private PetDaycareService service;
    @Autowired
    private AmazonS3Client amazonS3Client;


    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("randomPet", service.getPetToday());
        return "home";
    }
    @GetMapping("/allPets")
    public String allPetsPage(Model model){
        model.addAttribute("allPets", service.getAllPets());
        return "allPets";
    }

    @GetMapping("/addPet")
    public String addPetPage(Model model){
        Random random = new Random();
        model.addAttribute("randomNum", random.nextInt(2147000000));
        return "addPet";
    }

//    @PostMapping("/uploadImage")
//    public void uploadImage(){
//        amazonS3Client.putObject(new PutObjectRequest())
//    }







}
