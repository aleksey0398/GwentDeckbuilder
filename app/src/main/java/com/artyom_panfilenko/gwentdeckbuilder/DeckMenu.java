package com.artyom_panfilenko.gwentdeckbuilder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class DeckMenu extends AppCompatActivity{
    RecyclerView rvAlCards,rvDeckCards;
    ArrayList<Card> cards = new ArrayList<>();
    ArrayList<DeckCard> deckCards = new ArrayList<>();
    TextView txtFaction;
    TextView txtName;
    Spinner spLeaders;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deck);

        String name = (String)getIntent().getSerializableExtra("name");
        String faction = (String)getIntent().getSerializableExtra("faction");
        String leader = (String)getIntent().getSerializableExtra("leader");

        txtFaction = findViewById(R.id.txt_faction);
        txtName = findViewById(R.id.txt_name);
        spLeaders = findViewById(R.id.spinner);
        txtName.setText(name);
        txtFaction.setText("Faction:"+faction);
        switch(faction){
            case "Monsters":{
                ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,R.array.Monsters_leaders,android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLeaders.setAdapter(adapter);
            }
            case "Nilfgaard":{
                ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,R.array.Nilfgaard_leaders,android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLeaders.setAdapter(adapter);
            }
            case "Northern Realms":{
                ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,R.array.Norhern_Relalms_leaders,android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLeaders.setAdapter(adapter);
            }
            case "Scoiatael":{
                ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,R.array.Scoiatael_leaders,android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLeaders.setAdapter(adapter);
            }
            case "Skellige":{
                ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this,R.array.Skellige_leaders,android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLeaders.setAdapter(adapter);
            }

        }

        ArrayAdapter<String> adapter = (ArrayAdapter)spLeaders.getAdapter();
        spLeaders.setSelection(adapter.getPosition(leader));

        rvAlCards = findViewById(R.id.rv_all_cards);
        CardHelper ch = new CardHelper(this);
        cards = ch.getAll();
        rvAlCards.setLayoutManager(new LinearLayoutManager(this));
        rvAlCards.setItemAnimator(new DefaultItemAnimator());
        rvAlCards.setAdapter(new CardsRVAdapter(cards,this));

        rvDeckCards = findViewById(R.id.rv_deck_cards);
        cards = ch.getAll();
        rvAlCards.setLayoutManager(new LinearLayoutManager(this));
        rvAlCards.setItemAnimator(new DefaultItemAnimator());
        rvAlCards.setAdapter(new CardsRVAdapter(cards,this));


    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
