package com.example.laura.petadoption.repository;

import com.example.laura.petadoption.model.Pet;

import java.util.List;

/**
 * Created by Laura on 12/17/2016.
 */

public interface IPetRepo {
    Pet addPet(Pet pet);
    void removePet(int id);
    Pet updatePet(Pet  pet);
    Pet getPet(int id);
    List<Pet> getAllPets();

}
