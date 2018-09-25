package com.artyom_panfilenko.gwentdeckbuilder.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.artyom_panfilenko.gwentdeckbuilder.Card;
import com.artyom_panfilenko.gwentdeckbuilder.Deck;
import com.artyom_panfilenko.gwentdeckbuilder.DeckCard;
import com.artyom_panfilenko.gwentdeckbuilder.R;
import com.artyom_panfilenko.gwentdeckbuilder.adapters.CardsRVAdapter;
import com.artyom_panfilenko.gwentdeckbuilder.adapters.DeckCardsRVAdapter;
import com.artyom_panfilenko.gwentdeckbuilder.database.CardHelper;
import com.artyom_panfilenko.gwentdeckbuilder.database.DeckHelper;

import java.util.ArrayList;

public class DeckMenuActivity extends AppCompatActivity {
    RecyclerView rvAllCards;
    RecyclerView rvDeckCards;
    ArrayList<Card> cards = new ArrayList<>();
    static ArrayList<DeckCard> deckCards = new ArrayList<>();
    TextView txtFaction;
    TextView txtName;
    Spinner spLeaders;
    Boolean newDeck;
    Deck deck = new Deck();
    String name;
    String faction;
    String leader;
    static DeckCardsRVAdapter deckCardsRVAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deck);


        if (getIntent().hasExtra("deck")) {
            deck = (Deck) getIntent().getSerializableExtra("deck");
            name = deck.getName();
            faction = deck.getFaction();
            leader = deck.getLeader();
            newDeck = false;
        } else {
            name = (String) getIntent().getSerializableExtra("name");
            faction = (String) getIntent().getSerializableExtra("faction");
            leader = (String) getIntent().getSerializableExtra("leader");
            newDeck = true;
        }
        txtFaction = findViewById(R.id.txt_faction);
        txtName = findViewById(R.id.txt_name);
        spLeaders = findViewById(R.id.spinner);
        txtName.setText(name);
        txtFaction.setText("Faction:" + faction);
        switch (faction) {
            case "Monsters": {
                String[] monstersLeaders = {"Arachas Queen", "Dagon", "Eredin", "Unseen Elder"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monstersLeaders);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLeaders.setAdapter(adapter);
                break;
            }
            case "Nilfgaard": {
                String[] nilfgaardLeaders = {"Emhyr", "John Calveit", "MorvranVoorhis", "Usurper"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nilfgaardLeaders);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLeaders.setAdapter(adapter);
                break;
            }
            case "Northern Realms": {
                String[] northernRealmsLeaders = {"King Foltest", "King Henselt", "King Radovid", "Princess Adda"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, northernRealmsLeaders);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLeaders.setAdapter(adapter);
                break;
            }
            case "Scoiatael": {
                String[] scoiataelLeaders = {"Brouver Hoog", "Eithne", "Filavandrel", "Francesca Findebair"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, scoiataelLeaders);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLeaders.setAdapter(adapter);
                break;
            }
            case "Skellige": {
                String[] skelligeLeaders = {"Bran Tuirseach", "Crah an Craite", "Eist Tuirseach", "Harald the Cripple"};
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, skelligeLeaders);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLeaders.setAdapter(adapter);
                break;
            }

        }

        ArrayAdapter<String> spAdapter = (ArrayAdapter) spLeaders.getAdapter();
        spLeaders.setSelection(spAdapter.getPosition(leader));
        rvAllCards = findViewById(R.id.rv_all_cards);
        CardHelper ch = new CardHelper(this);
        cards = ch.getAll();
        for (int i = 0; i < cards.size(); i++) {
            Log.d("cards " + String.valueOf(i), "name=" + cards.get(i).getName());
        }
        rvAllCards.setLayoutManager(new LinearLayoutManager(this));
        rvAllCards.setItemAnimator(new DefaultItemAnimator());
        rvAllCards.setAdapter(new CardsRVAdapter(cards, this));

        if (!newDeck) {
            try {
                String[] idCards = deck.getCards().split(" ");
                for (int i = 1; i < idCards.length; i = i + 2) {
                    int id = Integer.getInteger(idCards[i - 1]);
                    int n = Integer.getInteger(idCards[i]);
                    Card card = ch.getCard(id);
                    deckCards.add(new DeckCard(id, card.getName(), card.getTags(),
                            card.getDescription(), card.getType(), card.getRarity(), card.getFaction(), n));
                }
            } catch (NullPointerException e){

            }
        }

        deckCardsRVAdapter = new DeckCardsRVAdapter(deckCards);
        rvDeckCards = findViewById(R.id.rv_deck_cards);
        rvAllCards.setLayoutManager(new LinearLayoutManager(this));
        rvAllCards.setItemAnimator(new DefaultItemAnimator());
        rvAllCards.setAdapter(deckCardsRVAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        String str = null;
        for(int i = 0;i < deckCards.size();i++){
          str = str + deckCards.get(i).getId() + " " + deckCards.get(i).getNum() + " ";
        }
        DeckHelper dh = new DeckHelper(this);
        Deck deckForSave = new Deck(deck.getId(),String.valueOf(txtName.getText()),faction,String.valueOf(spLeaders.getSelectedItem()),str);
        if(newDeck){
            deck.setId((int)dh.insert(deckForSave));
            newDeck = false;
        }else {
            dh.update(deckForSave);
        }
    }
    public static void addCard(Card card){
        DeckCard deckCard = (DeckCard)card;
        if(deckCard.getType()=="bronze"){
            int n = deckCards.size();
            Boolean contains = false;
            for(int i = 1;i < 3;i++){
                deckCard.setNum(i);
                if(deckCards.contains(deckCard)){
                    deckCard.setNum(deckCard.getNum()+1);
                    n = deckCards.indexOf(deckCard);
                    deckCards.set(n,deckCard);
                    contains = true;
                    deckCardsRVAdapter.notifyItemChanged(n);
                    break;
                }
            }
            if (!contains){
                deckCards.add(deckCard);
                deckCardsRVAdapter.notifyItemChanged(n);
            }
        }else{
            deckCard.setNum(1);
            if(!deckCards.contains(deckCard)){
                deckCards.add(deckCard);
                deckCardsRVAdapter.notifyItemChanged(deckCards.size()-1);
            }
        }
    }


}
