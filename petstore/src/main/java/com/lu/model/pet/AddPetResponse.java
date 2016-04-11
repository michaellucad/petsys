package com.lu.model.pet;

import com.lu.model.Response;

public class AddPetResponse extends Response{
	private Pet pet;

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}
}
