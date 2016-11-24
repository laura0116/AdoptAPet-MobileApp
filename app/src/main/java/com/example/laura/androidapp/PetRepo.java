package com.example.laura.androidapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Laura on 11/24/2016.
 */

public class PetRepo extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_repo);


        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for(int i=0; i<4; i++) {
            Map<String, String> datam = new HashMap<>();
            datam.put("name", "pet "+ Integer.toString(i));
            datam.put("species", "dog");
            data.add(datam);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"name", "species"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});

        final ListView petList = (ListView) findViewById(R.id.PetList);
        petList.setAdapter(adapter);

        petList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent editItem = new Intent(PetRepo.this, PetListEdit.class);
                Map<String, String> selectedFromList = (Map<String, String>) petList.getItemAtPosition(i);
                editItem.putExtra("name", selectedFromList.get("name"));
                editItem.putExtra("type", selectedFromList.get("type"));

                startActivity(editItem);
            }
        });
    }
}
