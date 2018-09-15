package com.artyom_panfilenko.gwentdeckbuilder.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.artyom_panfilenko.gwentdeckbuilder.DeckCard;
import com.artyom_panfilenko.gwentdeckbuilder.R;

import java.util.List;

public class DeckCardsRVAdapter extends RecyclerView.Adapter<DeckCardsRVAdapter.DeckCardsViewHolder> {
    private List<DeckCard> cards;
    private Context context;

    public DeckCardsRVAdapter(List<DeckCard> cards, Context context) {
        this.cards = cards;
        this.context = context;
    }

    @Override
    public DeckCardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rv, parent, false);
        return new DeckCardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeckCardsViewHolder holder, int position) {
        final DeckCard card = cards.get(position);
        holder.txtDeckCard.setText(card.getName() + " " + String.valueOf(card.getNum()));
        switch (card.getRarity()) {

        }
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    class DeckCardsViewHolder extends RecyclerView.ViewHolder {

        TextView txtDeckCard;

        DeckCardsViewHolder(View itemView) {
            super(itemView);
            txtDeckCard = itemView.findViewById(R.id.txt_deck_card);
        }
    }
}
