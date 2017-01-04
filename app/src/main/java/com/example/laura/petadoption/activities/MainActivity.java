package com.example.laura.petadoption.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.laura.petadoption.R;
import com.example.laura.petadoption.repository.PetRepo;
import com.example.laura.petadoption.fragments.Chart;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button petRepoButton = (Button) findViewById(R.id.PetRepo);
        petRepoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PetListActivity.class));
            }
        });

        Button emailSendButton = (Button) findViewById(R.id.EmailSend);
        emailSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] TO = {"someone@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                TextView emailBody = (TextView) findViewById(R.id.EmailBody);

                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                startActivity(emailIntent);
            }
        });
        Button chartButton = (Button) findViewById(R.id.ChartButton);
        chartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Chart().show(getFragmentManager(), "chart");
            }
        });
    }
}
