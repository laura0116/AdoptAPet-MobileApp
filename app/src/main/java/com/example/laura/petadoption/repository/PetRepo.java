package com.example.laura.petadoption.repository;

import com.example.laura.petadoption.model.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laura on 11/24/2016.
 */

public class PetRepo implements IPetRepo{
    private List<Pet> pets;
    private String filename;

    public PetRepo() {
        this.pets =  new ArrayList<>();
    }

    public PetRepo(String filename){
        this.filename = filename;
    }

    @Override
    public Pet addPet(Pet pet) {
        pet.setId(pets.size());
        if (pets.add(pet)){
            return pets.get(pets.size() - 1);
        }
        return null;
    }

    @Override
    public void removePet(int id) {
        pets.remove(id);
    }

    @Override
    public Pet updatePet(Pet pet) {
        pets.set(pet.getId(), pet);
        return pets.get(pet.getId());
    }

    @Override
    public Pet getPet(int id) {
        return pets.get(id);
    }

    @Override
    public List<Pet> getAllPets() {
        return pets;
    }


}
