package com.example.studente.pgcreator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CharactersAdapter extends BaseAdapter {

    private List<Character> dataset;

    private static class CharacterViewHolder {

        public TextView nome;
        public TextView razza;
        public TextView classe;

        public CharacterViewHolder(View itemView) {
            nome = (TextView) itemView.findViewById(R.id.textViewNome);
            razza = (TextView) itemView.findViewById(R.id.textViewRazza);
            classe = (TextView) itemView.findViewById(R.id.textViewClasse);
        }
    }

    public CharactersAdapter() {
        this.dataset = new ArrayList<>();
    }

    public void setCharacters(List<Character> characters) {
        dataset = characters;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CharacterViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.pg_item_layout, parent, false);
            holder = new CharacterViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CharacterViewHolder) convertView.getTag();
        }

        Character character = getItem(position);
        holder.nome.setText(character.getNome());
        holder.razza.setText(character.getRazza());
        holder.classe.setText(character.getClasse());

        return convertView;
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public Character getItem(int position) {
        return dataset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
