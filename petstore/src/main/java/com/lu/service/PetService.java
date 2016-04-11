/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.lu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lu.dao.IPetDao;
import com.lu.model.pet.Pet;
import com.lu.model.pet.SearchPetRequest;
import com.lu.model.pet.SearchPetResponse;

@Service
public class PetService implements IPetService {
	@Autowired
	IPetDao petDao;
	@Override
	public void deletePet(Pet pet) {
		petDao.deletePet(pet);
	}

	@Override
	public void addPet(Pet pet) {
		petDao.addPet(pet);
	}

	@Override
	public SearchPetResponse searchPet(SearchPetRequest req) {
		return petDao.searchPet(req);
	}


}
