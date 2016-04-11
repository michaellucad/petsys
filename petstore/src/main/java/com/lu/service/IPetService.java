package com.lu.service;

import com.lu.model.pet.Pet;
import com.lu.model.pet.SearchPetRequest;
import com.lu.model.pet.SearchPetResponse;

public interface IPetService {
	void deletePet(Pet pet);
	void addPet(Pet pet);
	SearchPetResponse searchPet(SearchPetRequest req);
}
