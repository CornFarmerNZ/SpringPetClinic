package com.CornFarmerNZ.PetDaycare.repository;

import com.CornFarmerNZ.PetDaycare.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
}
