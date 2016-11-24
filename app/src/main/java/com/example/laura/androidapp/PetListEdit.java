package com.example.laura.androidapp;

/**
 * Created by Laura on 11/24/2016.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.TextView;

import org.json.JSONObject;

public class PetListEdit extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list_edit);

        Bundle extras = getIntent().getExtras();

        TextView editName = (TextView) findViewById(R.id.EditPetName);
        TextView editType = (TextView) findViewById(R.id.EditPetAge);
        editName.setText(extras.getString("name"));
        editType.setText(extras.getString("age"));
    }
}
