package com.artyom_panfilenko.gwentdeckbuilder.activities;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.artyom_panfilenko.gwentdeckbuilder.R;

public class NewDeckActivity extends AppCompatActivity {
    EditText txtName;
    Spinner spFaction;
    Spinner spLeader;
    Button btnCreate;
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_deck);
        txtName = findViewById(R.id.txt_name_of_new_deck);
        spFaction = findViewById(R.id.sp_faction);
        spLeader = findViewById(R.id.sp_leader);
        btnCreate = findViewById(R.id.btn_create_new_deck);
        context = this;
        spFaction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                switch (i) {
                    case 0: {
                        String[] monstersLeaders = {"Arachas Queen","Dagon","Eredin","Unseen Elder"};
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,monstersLeaders);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spLeader.setAdapter(adapter);
                        Log.d("onItemSelected", String.valueOf(i));
                        break;
                    }
                    case 1: {
                        String[] nilfgaardLeaders = {"Emhyr","John Calveit","MorvranVoorhis","Usurper"};
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,nilfgaardLeaders);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spLeader.setAdapter(adapter);
                        Log.d("onItemSelected", String.valueOf(i));
                        break;
                    }
                    case 2: {
                        String[] northernRealmsLeaders = {"King Foltest","King Henselt","King Radovid","Princess Adda"};
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,northernRealmsLeaders);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spLeader.setAdapter(adapter);
                        Log.d("onItemSelected", String.valueOf(i));
                        break;
                    }
                    case 3: {
                        String[] scoiataelLeaders = {"Brouver Hoog","Eithne","Filavandrel","Francesca Findebair"};
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,scoiataelLeaders);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spLeader.setAdapter(adapter);
                        Log.d("onItemSelected", String.valueOf(i));
                        break;
                    }
                    case 4: {
                        String[] skelligeLeaders = {"Bran Tuirseach","Crah an Craite","Eist Tuirseach","Harald the Cripple"};
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item,skelligeLeaders);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spLeader.setAdapter(adapter);
                        Log.d("onItemSelected", String.valueOf(i));
                        break;
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewDeckActivity.this, DeckMenuActivity.class);
                intent.putExtra("name", txtName.getText().toString());
                intent.putExtra("faction", (String) spFaction.getSelectedItem());
                intent.putExtra("leader", (String) spLeader.getSelectedItem());
                startActivity(intent);
            }
        });
    }
}
