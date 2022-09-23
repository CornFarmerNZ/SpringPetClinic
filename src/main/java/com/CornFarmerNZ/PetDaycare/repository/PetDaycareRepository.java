package com.CornFarmerNZ.PetDaycare.repository;

import com.CornFarmerNZ.PetDaycare.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetDaycareRepository extends JpaRepository<Pet, Long> {
}
