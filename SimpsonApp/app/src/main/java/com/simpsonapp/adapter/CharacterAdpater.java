package com.simpsonapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simpsonapp.R;
import com.simpsonapp.models.Characters;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterAdpater extends RecyclerView.Adapter<CharacterAdpater.CharacterViewHolder> {

    List<Characters> lstCharacters;

    Context ctx;

    final String URL_IMAGE_BASE = "https://cdn.thesimpsonsapi.com/200";

    public CharacterAdpater(List<Characters> lstCharacters, Context ctx) {
        this.lstCharacters = lstCharacters;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.characters_adapter, parent, false);
        CharacterViewHolder viewHolder = new CharacterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Characters objCharacters = lstCharacters.get(position);
        holder.tvNameCharacter.setText(objCharacters.getName());
        String urlImgCharacters = URL_IMAGE_BASE + objCharacters.getPortrait_path();
        Picasso.get()
                .load(urlImgCharacters)
                .into(holder.imgCharacter);
    }

    @Override
    public int getItemCount() {
        return lstCharacters.size();
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCharacter;
        TextView tvNameCharacter;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCharacter = itemView.findViewById(R.id.img_character);
            tvNameCharacter = itemView.findViewById(R.id.tv_character);
        }
    }
}
