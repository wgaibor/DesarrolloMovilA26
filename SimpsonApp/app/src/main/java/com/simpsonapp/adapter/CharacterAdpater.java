package com.simpsonapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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
        int ageCharacter = objCharacters.getAge();
        if(ageCharacter > 0) {
            holder.tvAgeCharacter.setText(objCharacters.getAge()+"");
            holder.tvAgeCharacter.setVisibility(View.VISIBLE);
        } else {
            holder.tvAgeCharacter.setVisibility(View.GONE);
        }
        holder.tvOcuppation.setText(objCharacters.getOccupation());
        if(objCharacters.getStatus().equalsIgnoreCase("Deceased")){
            holder.tvStatus.setBackground(
                    ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.rectangle_status_deceased_textview) );
        } else {
            holder.tvStatus.setBackground(
                    ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.rectangle_status_alive_textview) );
        }
        holder.tvStatus.setText(objCharacters.getStatus());

        String[] phrases = objCharacters.getPhrases();
        String firstPhrase = (phrases != null && phrases.length > 0) ? phrases[0] : "Sin frase";
        holder.tvPhrase.setText(firstPhrase);
    }

    @Override
    public int getItemCount() {
        return lstCharacters.size();
    }

    public void addCharacterNew(List<Characters> lstCharacter) {
        int initialPosition = lstCharacters.size();
        lstCharacters.addAll(lstCharacter);
        notifyItemRangeInserted(initialPosition, lstCharacters.size());
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCharacter;
        TextView tvNameCharacter;
        TextView tvAgeCharacter;
        TextView tvOcuppation;
        TextView tvStatus;
        TextView tvPhrase;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCharacter = itemView.findViewById(R.id.img_character);
            tvNameCharacter = itemView.findViewById(R.id.tv_character);
            tvAgeCharacter = itemView.findViewById(R.id.tv_age);
            tvOcuppation = itemView.findViewById(R.id.tv_occupation);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvPhrase = itemView.findViewById(R.id.tv_phrase);
        }
    }
}
