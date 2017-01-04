package com.example.laura.petadoption.repository;

import com.example.laura.petadoption.model.Pet;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class RealmPetRepo implements IPetRepo {

    private Realm realm;

    public RealmPetRepo(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Pet addPet(Pet pet) {
        List<Pet> pets = getAllPets();
        pet.setId(pets.size() > 0 ? pets.get(pets.size() - 1).getId() + 1 : 0);
        realm.beginTransaction();
        realm.insert(pet);
        realm.commitTransaction();
        return pet;
    }

    @Override
    public void removePet(int id) {
        realm.beginTransaction();
        realm.where(Pet.class).equalTo("id",id).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }

    @Override
    public Pet updatePet(Pet pet) {
        realm.beginTransaction();
        RealmResults<Pet> rows = realm.where(Pet.class).equalTo("id",pet.getId()).findAll();
        for (Pet a: rows){
            a.setName(pet.getName());
            a.setSpecies(pet.getSpecies());
            a.setAge(pet.getAge());
        }
        realm.commitTransaction();
        return pet;
    }

    @Override
    public Pet getPet(int id) {
        return realm.where(Pet.class).equalTo("id",id).findFirst();
    }

    @Override
    public List<Pet> getAllPets() {
        return realm.where(Pet.class).findAll();
    }
}
