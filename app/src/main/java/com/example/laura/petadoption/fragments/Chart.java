package com.example.laura.petadoption.fragments;

/**
 * Created by Laura on 1/4/2017.
 */


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.laura.petadoption.R;
import com.example.laura.petadoption.activities.PetListActivity;
import com.example.laura.petadoption.controller.PetController;
import com.example.laura.petadoption.model.Pet;
import com.example.laura.petadoption.repository.PetRepo;
import com.example.laura.petadoption.repository.RealmPetRepo;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;


@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class Chart extends DialogFragment {
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View chartView = inflater.inflate(R.layout.chart, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        Realm.init(getContext());
        PetController dummyController = new PetController(new RealmPetRepo(Realm.getDefaultInstance()));
        List<PieEntry> entries = new ArrayList<>();
        List<LegendEntry> labels = new ArrayList<>();
        Map<String, Integer> ages = new HashMap<>();
        for (Pet pet : dummyController.getAllPets()){
            int age = pet.getAge();
            if (ages.containsKey(age)){
                ages.put(String.valueOf(age), ages.get(age) + 1);
            } else {
                ages.put(String.valueOf(age), 1);
            }
        }
        int i = 0;
        for (String key : ages.keySet()){
            LegendEntry entry = new LegendEntry();
            entry.label = key;
            entry.formColor = ColorTemplate.VORDIPLOM_COLORS[i];
            labels.add(entry);
            entries.add(new PieEntry(ages.get(key)));
            i++;
        }
        PieChart chart = (PieChart) chartView.findViewById(R.id.chart);
        Legend legend =chart.getLegend();
        legend.setEnabled(true);
        legend.setCustom(labels);
        PieDataSet dataset = new PieDataSet(entries, "");
        dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
        PieData data = new PieData(dataset);
        chart.setData(data);
        Description desc = new Description();
        desc.setEnabled(true);
        desc.setText("ages");
        chart.setDescription(desc);
        builder.setView(chartView);
        return builder.create();
    }
}
