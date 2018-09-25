package com.artyom_panfilenko.gwentdeckbuilder.adapters;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.artyom_panfilenko.gwentdeckbuilder.DeckCard;
import com.artyom_panfilenko.gwentdeckbuilder.R;
import com.artyom_panfilenko.gwentdeckbuilder.activities.DeckMenuActivity;

import java.util.ArrayList;

public class DeckCardsRVAdapter extends RecyclerView.Adapter<DeckCardsRVAdapter.DeckCardsViewHolder> {
    private ArrayList<DeckCard> cards;

    public DeckCardsRVAdapter(ArrayList<DeckCard> cards) {
        this.cards = cards;
    }

    @Override
    public DeckCardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rv, parent, false);
        return new DeckCardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeckCardsViewHolder holder, final int position) {
        final DeckCard card = cards.get(position);
        holder.txtDeckCard.setText(card.getName() + " " + String.valueOf(card.getNum()));
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card.setNum(card.getNum()-1);
                if(card.getNum()==0) {
                    cards.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position,cards.size());
                }else{
                    cards.set(position,card);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    class DeckCardsViewHolder extends RecyclerView.ViewHolder {

        TextView txtDeckCard;
        CardView cv;

        DeckCardsViewHolder(View itemView) {
            super(itemView);
            txtDeckCard = itemView.findViewById(R.id.txt_deck_card);
            cv = itemView.findViewById(R.id.card_rv_card);
        }
    }

}
