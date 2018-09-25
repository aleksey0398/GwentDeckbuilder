package com.artyom_panfilenko.gwentdeckbuilder.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.artyom_panfilenko.gwentdeckbuilder.Deck;
import com.artyom_panfilenko.gwentdeckbuilder.activities.DeckMenuActivity;
import com.artyom_panfilenko.gwentdeckbuilder.R;
import com.artyom_panfilenko.gwentdeckbuilder.activities.MainActivity;
import com.artyom_panfilenko.gwentdeckbuilder.database.DeckHelper;

import java.util.LinkedList;

public class DeckRVAdapter extends RecyclerView.Adapter<DeckRVAdapter.DeckViewHolder> {

    private LinkedList<Deck> decks;
    private Context context;

    public DeckRVAdapter(LinkedList<Deck> decks, Context context) {
        this.decks = decks;
        this.context = context;
    }

    @Override
    public DeckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deck_rv, parent, false);
        return new DeckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeckViewHolder holder, final int position) {
        final Deck deck = decks.get(position);
        holder.name.setText(deck.getName());
        holder.clickListener.setRecord(deck);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeckHelper dh = new DeckHelper(context);
                dh.delete(deck);
                decks.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,decks.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return decks.size();
    }

    class DeckViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        CardView cv;
        Button btnDelete;
        CardViewClickListener clickListener = new CardViewClickListener();

        DeckViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_deck_name);
            image = itemView.findViewById(R.id.img_leader);
            cv = itemView.findViewById(R.id.deck_rv_card);
            cv.setOnClickListener(clickListener);
            btnDelete = itemView.findViewById(R.id.btn_delete_deck);
        }
    }


    public class CardViewClickListener implements View.OnClickListener {

        Deck deck;

        public void setRecord(Deck deck) {
            this.deck = deck;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DeckMenuActivity.class);
            intent.putExtra("deck", deck);
            context.startActivity(intent);
        }
    }

}
