package com.example.laura.petadoption.controller;

import com.example.laura.petadoption.model.Pet;
import com.example.laura.petadoption.repository.IPetRepo;

import java.util.List;

/**
 * Created by Laura on 12/18/2016.
 */

public class PetController {
    private IPetRepo repo;

    public PetController(IPetRepo repo) {
        this.repo = repo;
    }

    public Pet addPet(String name, String species, int age){
        return repo.addPet(new Pet(name,species,age));
    }

    public void removePet(int id){
        repo.removePet(id);
    }

    public Pet updatePet(int id, String name, String species,int age){
        Pet pet = new Pet(name,species,age);
        pet.setId(id);
        return repo.updatePet(pet);
    }

    public Pet getPet(int id){
        return repo.getPet(id);
    }

    public List<Pet> getAllPets(){
        return repo.getAllPets();
    }

}
