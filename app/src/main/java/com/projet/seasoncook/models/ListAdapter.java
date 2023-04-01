package com.projet.seasoncook.models;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projet.seasoncook.R;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    List<Recette> recettes;
    Context context;
    LayoutInflater inflater;

    public ListAdapter(List<Recette> recettes, Context context){
        this.recettes = recettes;
        this.context = context;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return this.recettes.size();
    }

    @Override
    public Recette getItem(int i) {
        return this.recettes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.itemlist, null);
        TextView name = (TextView) view.findViewById(R.id.nameRecetteList);
        name.setText(this.recettes.get(i).getTitle());

        ImageView icon = (ImageView) view.findViewById(R.id.iconRecetteList);
        int resImage = this.context.getResources().getIdentifier(this.recettes.get(i).getImageLink(), "drawable", this.context.getPackageName());
        icon.setImageDrawable(this.context.getResources().getDrawable(resImage));
        return view;
    }
}
