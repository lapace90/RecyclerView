package com.example.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MonAdapter extends RecyclerView.Adapter<MonAdapter.MonViewHolder> {
    List<Items> myItems;
    //Context context;
    public MonAdapter(List<Items> myItems) {
        this.myItems = myItems;
        //this.context = context;
    }

    @NonNull
    @Override
    public MonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        // (inflate:gonfler) je charge ma vue en créant un objet view qui contient mon layout
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.affichage_items, parent, false);
        return new MonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonViewHolder holder, int position) {
    //(holer:titulaire) maj de la vue en cours de fontion de la position
        holder.imgV.setImageResource(myItems.get(position).getImg());
        holder.titleTxt.setText(myItems.get(position).getTitle());
        holder.descriptionTxt.setText(myItems.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        //return 0;
        return myItems.size();
    }

    public class MonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTxt, descriptionTxt;
        private ImageView imgV;

        public MonViewHolder(@NonNull View itemView) {
            super(itemView);
            //lien entre item et xml
            titleTxt = itemView.findViewById(R.id.title);
            descriptionTxt = itemView.findViewById(R.id.description);
            imgV = itemView.findViewById(R.id.img);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int posi = getLayoutPosition();
            String titleClick = myItems.get(posi).getTitle();
            String descriClick = myItems.get(posi).getDescription();
            int imgClick = myItems.get(posi).getImg();
            Log.d("CLICK", "Title: " + titleClick);
            Log.d("CLICK", "Description: " + descriClick);
            Log.d("CLICK", "Image: " + imgClick);
            Toast.makeText(this.itemView.getContext(), "Play " + titleClick + " now!", Toast.LENGTH_LONG).show();

        }
    }
}
