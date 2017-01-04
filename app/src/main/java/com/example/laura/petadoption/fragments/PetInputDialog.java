package com.example.laura.petadoption.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.laura.petadoption.R;
import com.example.laura.petadoption.activities.PetListActivity;

/**
 * Created by Laura on 12/18/2016.
 */

public class PetInputDialog extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle args = getArguments();
        final boolean addState = args.getBoolean("addState");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View petInputView = inflater.inflate(R.layout.pet_input, null);

        final EditText name = (EditText) petInputView.findViewById(R.id.name);
        final EditText species = (EditText) petInputView.findViewById(R.id.species);
        final NumberPicker age = (NumberPicker) petInputView.findViewById(R.id.age);
        age.setMaxValue(5000);


        if (!addState) {
           name.setText(args.getString("name"));
           species.setText(args.getString("species"));
            age.setValue(args.getInt("age"));
        }
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(petInputView)
                // Add action buttons
                .setPositiveButton(addState ? "ADD" : "EDIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        PetListActivity list = (PetListActivity) getActivity();
                        if (!addState){
                            list.updatePet(args.getInt("id"), name.getText().toString(), species.getText().toString(),age.getValue());
                        } else {
                            list.addPet(name.getText().toString(),species.getText().toString(),age.getValue());
                        }
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        PetInputDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
