package com.lu.dao;

import com.lu.model.pet.AddPetResponse;
import com.lu.model.pet.Pet;
import com.lu.model.pet.SearchPetRequest;
import com.lu.model.pet.SearchPetResponse;

public interface IPetDao {
	void deletePet(Pet pet);
	AddPetResponse addPet(Pet pet);
	SearchPetResponse searchPet(SearchPetRequest req);
}
