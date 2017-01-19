package com.example.laura.petadoption.repository;

import android.widget.ArrayAdapter;

import com.example.laura.petadoption.model.Pet;
import com.example.laura.petadoption.utils.Observer;
import com.example.laura.petadoption.utils.Subject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import io.realm.Realm;
import io.realm.RealmResults;


public class RealmPetRepo implements IPetRepo, Subject {

    private Realm realm;
    private DatabaseReference fireBaseDb;
    private List<Pet> pets = new ArrayList<>();
    private List<Observer> observers =  new ArrayList<>();

    private Pet fromMap(Map<String,Object> map){
        Pet pet = new Pet();
        pet.setId(Integer.parseInt(map.get("id").toString()));
        pet.setName(map.get("name").toString());
        pet.setSpecies(map.get("species").toString());
        pet.setAge(Integer.parseInt(map.get("age").toString()));
        return pet;
    }
    public void loadPets() {
        fireBaseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " pets");
                pets.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    for (DataSnapshot postSnapshot2: postSnapshot.getChildren()) {
                        Pet pet = fromMap((HashMap<String,Object> )postSnapshot2.getValue());
                        pets.add(pet);
                    }
                }
                System.out.println(pets);
                notifyObservers();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public RealmPetRepo(Realm realm, DatabaseReference fireBaseDb) {
        this.realm = realm;
        this.fireBaseDb = fireBaseDb;
    }

    @Override
    public Pet addPet(Pet pet) {
        List<Pet> pets = getAllPets();
        pet.setId(pets.size() > 0 ? pets.get(pets.size() - 1).getId() + 1 : 0);
        realm.beginTransaction();
        realm.insert(pet);
        realm.commitTransaction();
        Map<String,Object> added = new HashMap<>();
        added.put("/pets/" + pet.getId(),pet.toMap());
        fireBaseDb.updateChildren(added);
        return pet;
    }

    @Override
    public void removePet(int id) {
        realm.beginTransaction();
        realm.where(Pet.class).equalTo("id",id).findAll().deleteAllFromRealm();
        realm.commitTransaction();
        fireBaseDb.child("/pets/"+String.valueOf(id)).removeValue();
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
        Map<String,Object> added = new HashMap<>();
        added.put("/pets/" + pet.getId(),pet.toMap());
        fireBaseDb.updateChildren(added);
        return pet;
    }

    @Override
    public Pet getPet(int id) {
        return realm.where(Pet.class).equalTo("id",id).findFirst();
    }

    @Override
    public List<Pet> getAllPets() {
        return pets;
    }


    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update("Update pet");
        }
    }

    @Override
    public void notifyObservers(String designName, String typeOfChange) {

    }
}
