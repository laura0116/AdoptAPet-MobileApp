package com.example.laura.petadoption.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import io.realm.RealmObject;

/**
 * Created by Laura on 12/17/2016.
 */

public class Pet extends RealmObject {
    private int id;
    private String name;
    private String species;
    private int age;

    public Pet(String name,String species,int age){
        this.name = name;
        this.species = species;
        this.age = age;
    }
    public Pet(){}
    public int getId(){return id;}
    public void setId(int id){ this.id = id;}
    public String getName(){ return name;}
    public void setName(String name) {this.name = name;}
    public String getSpecies(){return species;}
    public void setSpecies(String species) {this.species = species;}
    public int getAge(){return age;}
    public void setAge(int age) { this.age =age; }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id",id);
        result.put("name", name);
        result.put("species", species);
        result.put("age", age);
        return result;
    }

}
