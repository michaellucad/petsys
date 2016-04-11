package com.lu.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lu.model.pet.AddPetRequest;
import com.lu.model.pet.AddPetResponse;
import com.lu.model.pet.DeletePetRequest;
import com.lu.model.pet.DeletePetResponse;
import com.lu.model.pet.SearchPetRequest;
import com.lu.model.pet.SearchPetResponse;
import com.lu.service.IPetService;

@RestController
@RequestMapping("/pet")
public class PetController {
	@Autowired
	IPetService petService;
    @RequestMapping(value = "searchPet",  method={RequestMethod.POST,RequestMethod.GET} )
    public SearchPetResponse search(@RequestBody SearchPetRequest request) {
		return petService.searchPet(request);
    }

    
    @RequestMapping(value = "add",  method={RequestMethod.POST,RequestMethod.GET} )
    public AddPetResponse add(@RequestBody AddPetRequest request) {
    	petService.addPet(request.getPet());
    	return new AddPetResponse();
    }
    
    @RequestMapping(value = "delete",  method={RequestMethod.POST,RequestMethod.GET} )
    public DeletePetResponse delete(@RequestBody DeletePetRequest request) {
		 petService.deletePet(request.getPet());
		 return new DeletePetResponse();
    }
}
