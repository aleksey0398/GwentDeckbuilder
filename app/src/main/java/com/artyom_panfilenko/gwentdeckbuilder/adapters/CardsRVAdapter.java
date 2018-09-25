package com.artyom_panfilenko.gwentdeckbuilder.adapters;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.artyom_panfilenko.gwentdeckbuilder.Card;
import com.artyom_panfilenko.gwentdeckbuilder.R;
import com.artyom_panfilenko.gwentdeckbuilder.activities.DeckMenuActivity;

import java.util.ArrayList;
import java.util.List;

public class CardsRVAdapter extends RecyclerView.Adapter<CardsRVAdapter.CardsViewHolder> {

    private ArrayList<Card> cards;
    private Context context;

    public CardsRVAdapter(ArrayList<Card> cards, Context context) {
        this.cards = cards;
        this.context = context;
    }

    @Override
    public CardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rv, parent, false);
        return new CardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardsViewHolder holder, int position) {
        final Card card = cards.get(position);
        holder.txtName.setText(card.getName());
        holder.txtTags.setText(card.getTags());
        holder.txtDescription.setText(card.getDescription());
        holder.clickListener.setRecord(card);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    class CardsViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtTags;
        ImageView image;
        TextView txtDescription;
        CardView cv;
        CardViewClickListener clickListener = new CardViewClickListener();

        CardsViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_card_name);
            txtTags = itemView.findViewById(R.id.txt_tags);
            image = itemView.findViewById(R.id.img_card);
            txtDescription = itemView.findViewById(R.id.txt_description);
            cv = itemView.findViewById(R.id.card_rv_card);
            cv.setOnClickListener(clickListener);
        }
    }
    public class CardViewClickListener implements View.OnClickListener {

        Card card;

        public void setRecord(Card card) {
            this.card = card;
        }

        @Override
        public void onClick(View view) {
            DeckMenuActivity.addCard(card);
        }
    }

}
