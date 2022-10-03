package com.CornFarmerNZ.PetDaycare.controller;

import com.CornFarmerNZ.PetDaycare.entity.Pet;
import com.CornFarmerNZ.PetDaycare.repository.PetDaycareRepository;
import com.CornFarmerNZ.PetDaycare.service.PetDaycareService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addPet")
@Log4j2
public class PetDaycareAPI {

	@Autowired
	private PetDaycareService service;

	@Autowired
	private PetDaycareRepository repository;



	@PostMapping("")
	public Pet addPet(@RequestBody Pet pet){


		pet.setPetAge(Long.parseLong(""+pet.getPetAge()));
		pet.setPetId(repository.count());
		pet.setPetPicture("https://petclinicbucket51.s3.us-west-2.amazonaws.com/"+pet.getPetId()+".jpg");
		log.info("Adding pet"+pet.toString());
		return service.checkInPet(pet);
	}

}
