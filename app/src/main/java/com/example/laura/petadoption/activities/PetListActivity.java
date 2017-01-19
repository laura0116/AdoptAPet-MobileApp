package com.example.laura.petadoption.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.laura.petadoption.*;
import com.example.laura.petadoption.adapters.PetAdapter;
import com.example.laura.petadoption.controller.PetController;
import com.example.laura.petadoption.fragments.PetInputDialog;
import com.example.laura.petadoption.model.Pet;
import com.example.laura.petadoption.repository.IPetRepo;
import com.example.laura.petadoption.repository.RealmPetRepo;
import com.example.laura.petadoption.utils.Observer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by Laura on 12/18/2016.
 */

public class PetListActivity extends AppCompatActivity implements Observer {
    ListView petList;
    ArrayAdapter adapter;
    RealmPetRepo repo;
    PetController controller;
    private DatabaseReference mDatabase;


    public void updatePet(int id, String name,String species, int age){
        controller.updatePet(id,name,species,age);
        adapter.notifyDataSetChanged();
    }

    public void addPet(String name, String species,int age){
        System.out.println(controller.addPet(name,species,age).getName());
        adapter.notifyDataSetChanged();
    }

    public void removePet(int id){
        controller.removePet(id);
        adapter.notifyDataSetChanged();
    }

    public void setupAdapter(){
        Realm realm = Realm.getDefaultInstance();
        repo = new RealmPetRepo(realm,mDatabase);
        repo.registerObserver(this);
        repo.loadPets();
        controller =  new PetController(repo);

        setContentView(R.layout.activity_pet_list);
        adapter = new PetAdapter(this, controller.getAllPets());
    }

    public void addButton(){
        Button addButton = (Button) findViewById(R.id.addPet);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle state = new Bundle();
                state.putBoolean("addState", true);
                PetInputDialog addDialog = new PetInputDialog();
                addDialog.setArguments(state);
                addDialog.show(getFragmentManager(), "addDialog");
            }
        });
    }

    public void addListView(){
        petList = (ListView) findViewById(R.id.PetList);
        petList.setAdapter(adapter);
        petList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Pet currentPet = (Pet) adapter.getItem(i);
                Bundle state = new Bundle();

                state.putBoolean("addState", false);
                state.putString("name", currentPet.getName());
                state.putString("species", currentPet.getSpecies());
                state.putInt("age",currentPet.getAge());
                state.putInt("id", currentPet.getId());

                PetInputDialog editDialog = new PetInputDialog();
                editDialog.setArguments(state);
                editDialog.show(getFragmentManager(), "editDialog");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getApplicationContext());
        mDatabase=FirebaseDatabase.getInstance().getReference();
        setupAdapter();
        addButton();
        addListView();
    }

    @Override
    public void update(String message) {
        if (adapter != null){
            adapter.notifyDataSetChanged();
        }
    }
}

