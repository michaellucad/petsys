package com.lu.dao;

import org.junit.Before;
import org.junit.Test;

import com.lu.model.pet.AddPetResponse;
import com.lu.model.pet.Pet;

import junit.framework.TestCase;

public class PetDaoTest extends TestCase{
	private IPetDao petDao;
	@Override
	@Before
	public void setUp(){
		petDao = new PetDao();
	}
	@Test
	public void testAddPet(){
		Pet pet= createPet();
		AddPetResponse res = petDao.addPet(pet);
		assertTrue(res.getPet() != null);
		assertTrue(pet.getId()!=null);
	}
	
	private Pet createPet(){
		Pet pet = new Pet();
		pet.setCategory("dog");
		pet.setName("Justin");
		pet.setPhotoUrls("dog.jpg");
		return pet;
	}
}
