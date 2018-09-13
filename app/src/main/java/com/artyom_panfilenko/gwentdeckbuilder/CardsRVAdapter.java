package com.artyom_panfilenko.gwentdeckbuilder;


import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CardsRVAdapter extends RecyclerView.Adapter<CardsRVAdapter.CardsViewHolder>{

    private List<Card> cards;
    private Context context;

    public CardsRVAdapter(List<Card> cards, Context context) {
        this.cards = cards;
        this.context = context;
    }

    @Override
    public CardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rv,parent,false);
        return new CardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardsViewHolder holder, int position) {
    final Card card = cards.get(position);
     holder.txtName.setText(card.getName());
     holder.txtTags.setText(card.getTags());
     holder.txtDescription.setText(card.getDescription());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    class CardsViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        TextView txtTags;
        ImageView image;
        TextView txtDescription;

        CardsViewHolder(View itemView){
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_card_name);
            txtTags = itemView.findViewById(R.id.txt_tags);
            image = itemView.findViewById(R.id.img_card);
            txtDescription = itemView.findViewById(R.id.txt_description);
        }
    }

}
