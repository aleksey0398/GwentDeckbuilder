package com.artyom_panfilenko.gwentdeckbuilder;


import android.content.Context;
import android.content.Intent;
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

public class NewDeckActivity extends AppCompatActivity{
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


                switch(i){
                    case 0:{
                        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(context,R.array.Monsters_leaders,android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spLeader.setAdapter(adapter);
                        Log.d("onItemSelected",String.valueOf(i));
                    }
                    case 1:{
                        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(context,R.array.Nilfgaard_leaders,android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spLeader.setAdapter(adapter);
                        Log.d("onItemSelected",String.valueOf(i));
                    }
                    case 2:{
                        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(context,R.array.Norhern_Relalms_leaders,android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spLeader.setAdapter(adapter);
                        Log.d("onItemSelected",String.valueOf(i));
                    }
                    case 3:{
                        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(context,R.array.Scoiatael_leaders,android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spLeader.setAdapter(adapter);
                        Log.d("onItemSelected",String.valueOf(i));
                    }
                    case 4:{
                        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(context,R.array.Skellige_leaders,android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spLeader.setAdapter(adapter);
                        Log.d("onItemSelected",String.valueOf(i));
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
                Intent intent = new Intent(NewDeckActivity.this,DeckMenu.class);
                intent.putExtra("name",txtName.getText());
                intent.putExtra("faction",(String)spFaction.getSelectedItem());
                intent.putExtra("leader",(String)spLeader.getSelectedItem());
                startActivity(intent);
            }
        });
    }
}
