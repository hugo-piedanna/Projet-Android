package com.projet.seasoncook.models;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.projet.seasoncook.R;

import java.util.List;
import java.util.zip.Inflater;

public class ListAdapterIngredients extends BaseAdapter {

    private List<Ingredient> list;
    private Context context;
    private LayoutInflater inflater;

    public ListAdapterIngredients(List<Ingredient> list, Context context){
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int i) {
        return this.list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_list_ing, null);
        TextView name = (TextView) view.findViewById(R.id.ingredientListPref);
        name.setText(list.get(i).getName());

        //TODO check si présent dans la base de données
        CheckBox box = (CheckBox) view.findViewById(R.id.checkbox);
        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.v("[Check]",list.get(i).getName() + ( b ? " Check" : " Pas check"));
            }
        });
        return view;
    }
}
