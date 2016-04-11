package com.lu.model.pet;

import java.util.List;

import com.lu.model.Response;

public class SearchPetResponse  extends Response{
	List<Pet> pets;

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
}
