package com.lu.dao;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lu.model.pet.AddPetResponse;
import com.lu.model.pet.Pet;
import com.lu.model.pet.SearchPetRequest;
import com.lu.model.pet.SearchPetResponse;

@Service
public class PetDao implements IPetDao{
	private HashMap<Integer, Pet> repositories;
	private void init(){
		try {
			LineIterator lt = IOUtils.lineIterator(new FileReader("/myspace/petstore/pets.txt"));
			repositories = new HashMap<Integer, Pet>();
			while (lt.hasNext()) {
				String line = lt.next();
				String[] ds = line.split(",");
				Pet p = new Pet();
				p.setId(Integer.parseInt(ds[0]));
				p.setName(ds[1]);
				p.setTags(ds[2]);
				p.setCategory(ds[3]);
				p.setPhotoUrls(ds[4]);
				p.setStatus(ds[5]);
				repositories.put(p.getId(), p);
			}
		}catch (Exception e) {
			//todo
			e.printStackTrace();
		}
	}

	@Override
	public void deletePet(Pet pet) {
		if (repositories == null) init();
		repositories.remove(pet.getId());
	}

	@Override
	public AddPetResponse addPet(Pet pet) {
		if (repositories == null) init();
		//todo add validation
		pet.setId(getNextSeq());
		repositories.put(pet.getId(), pet);
		AddPetResponse res = new AddPetResponse();
		res.setPet(pet);
		return res;
	}
	
	private Integer getNextSeq() {
		int max = 0;
		for (Integer i : repositories.keySet()) {
			if (i>max) max=i;
		}
		return max+1;
	}

	@Override
	public SearchPetResponse searchPet(SearchPetRequest req) {
		if (repositories == null) init();
		SearchPetResponse res = new SearchPetResponse();
		ArrayList<Pet> pets = new ArrayList<Pet>();
		res.setPets(pets);
		if (StringUtils.isEmpty(req.getId())) {
			pets.addAll(repositories.values());
		}else {
			Pet pet = repositories.get(req.getId());
			pets.add(pet);
			
		}
		return res;
	}

}
