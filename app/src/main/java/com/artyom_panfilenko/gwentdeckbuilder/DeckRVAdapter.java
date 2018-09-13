package com.artyom_panfilenko.gwentdeckbuilder;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DeckRVAdapter extends RecyclerView.Adapter<DeckRVAdapter.DeckViewHolder>{

    private List<Deck> decks;
    private Context context;

    public DeckRVAdapter(List<Deck> decks, Context context) {
        this.decks = decks;
        this.context = context;
    }

    @Override
    public DeckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deck_rv,parent,false);
        return new DeckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeckViewHolder holder, int position) {
        final Deck deck = decks.get(position);
        holder.name.setText(deck.getName());
    }

    @Override
    public int getItemCount() {
        return decks.size();
    }

    class DeckViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;

        public DeckViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_deck_name);
            image = itemView.findViewById(R.id.img_leader);
        }
    }
}
