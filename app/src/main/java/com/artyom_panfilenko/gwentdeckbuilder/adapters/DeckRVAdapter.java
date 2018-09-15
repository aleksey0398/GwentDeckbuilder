package com.artyom_panfilenko.gwentdeckbuilder.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artyom_panfilenko.gwentdeckbuilder.Deck;
import com.artyom_panfilenko.gwentdeckbuilder.DeckMenu;
import com.artyom_panfilenko.gwentdeckbuilder.R;

import java.util.List;

public class DeckRVAdapter extends RecyclerView.Adapter<DeckRVAdapter.DeckViewHolder> {

    private List<Deck> decks;
    private Context context;

    public DeckRVAdapter(List<Deck> decks, Context context) {
        this.decks = decks;
        this.context = context;
    }

    @Override
    public DeckViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deck_rv, parent, false);
        return new DeckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeckViewHolder holder, int position) {
        final Deck deck = decks.get(position);
        holder.name.setText(deck.getName());
        holder.clickListener.setRecord(deck);
    }

    @Override
    public int getItemCount() {
        return decks.size();
    }

    class DeckViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;
        CardView cv;
        CardViewClickListener clickListener = new CardViewClickListener();

        DeckViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_deck_name);
            image = itemView.findViewById(R.id.img_leader);
            cv = itemView.findViewById(R.id.deck_rv_card);
            cv.setOnClickListener(clickListener);
        }
    }


    public class CardViewClickListener implements View.OnClickListener {

        Deck deck;

        public void setRecord(Deck deck) {
            this.deck = deck;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DeckMenu.class);
            intent.putExtra("deck", deck);
            context.startActivity(intent);
        }
    }

//    class onItemClickListenerOld implements RecyclerView.OnItemTouchListener {
//
//        String cards;
//
//        @Override
//        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//            return false;
//        }
//
//        @Override
//        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//            Intent intent = new Intent(context, DeckMenu.class);
//            intent.putExtra("cards", cards);
//            context.startActivity(intent);
//        }
//
//        @Override
//        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//        }
//
//        private void setCards(String cards) {
//            this.cards = cards;
//        }
//    }
}
