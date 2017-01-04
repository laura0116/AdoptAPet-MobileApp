package com.example.laura.petadoption.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.laura.petadoption.R;
import com.example.laura.petadoption.activities.PetListActivity;
import com.example.laura.petadoption.model.Pet;

import java.util.List;

/**
 * Created by Laura on 12/18/2016.
 */

public class PetAdapter extends ArrayAdapter<Pet>{
    private List<Pet> pets;
    private LayoutInflater mInflater;

    public PetAdapter(Context context, List<Pet> pets) {
        super(context, 0, pets);
        this.pets = pets;
        mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.pet,parent , false);
            holder = new ViewHolder();
            holder.name =(TextView) convertView.findViewById(R.id.name);
            holder.species =(TextView) convertView.findViewById(R.id.species);
            holder.age = (TextView) convertView.findViewById(R.id.age);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        Button deleteButton = (Button) convertView.findViewById(R.id.deletePet);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PetListActivity list = (PetListActivity) getContext();
                list.removePet(pets.get(position).getId());
            }
        });

        Pet pet = pets.get(position);
        holder.name.setText(pet.getName());
        holder.species.setText(pet.getSpecies());
        holder.age.setText(String.valueOf(pet.getAge()));
        holder.deletePet = deleteButton;

        return convertView;
    }

    private static class ViewHolder{
        TextView name;
        TextView species;
        TextView age;
        Button deletePet;

    }

}
