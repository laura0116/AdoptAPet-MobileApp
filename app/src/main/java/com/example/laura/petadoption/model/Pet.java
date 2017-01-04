package com.example.laura.petadoption.model;

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

}
