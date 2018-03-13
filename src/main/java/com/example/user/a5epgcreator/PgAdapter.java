package com.example.user.a5epgcreator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PgAdapter extends BaseAdapter {

    private List<Pg> dataset;

    private static class PgViewHolder {

        public TextView nome;
        public TextView razza;
        public TextView classe;

        public PgViewHolder(View itemView) {
            nome = (TextView) itemView.findViewById(R.id.textViewItemNome);
            razza = (TextView) itemView.findViewById(R.id.textViewItemRazza);
            classe = (TextView) itemView.findViewById(R.id.textViewItemClasse);
        }
    }

    public PgAdapter() {
        this.dataset = new ArrayList<>();
    }

    public void setPgList(List<Pg> pgs) {
        dataset = pgs;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PgViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pg, parent, false);
            holder = new PgViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (PgViewHolder) convertView.getTag();
        }

        Pg pg = (Pg) getItem(position);
        holder.nome.setText(pg.getNome());
        holder.razza.setText(pg.razza.getTarget().getNome());
        holder.classe.setText(pg.classe.getTarget().getNome());

        return convertView;
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public Object getItem(int position) {
        return dataset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
