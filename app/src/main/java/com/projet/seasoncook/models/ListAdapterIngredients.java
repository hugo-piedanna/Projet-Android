package com.projet.seasoncook.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.projet.seasoncook.R;
import com.projet.seasoncook.database.DatabaseHelper;

import java.util.List;
import java.util.zip.Inflater;

public class ListAdapterIngredients extends BaseAdapter {

    private List<Ingredient> list;
    private Context context;
    private LayoutInflater inflater;
    private SQLiteDatabase db;

    public ListAdapterIngredients(List<Ingredient> list, Context context){
        this.list = list;
        this.context = context;
        this.inflater = (LayoutInflater.from(context));
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        this.db = dbHelper.getReadableDatabase();
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


        Cursor cursor = db.query("allergy", new String[] {"name"}, "name=?", new String[] {list.get(i).getName()}, null, null, null);
        box.setChecked(cursor.moveToFirst());

        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                DatabaseHelper dbHelper = new DatabaseHelper(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if(b){
                    //Check
                    db.execSQL("INSERT INTO allergy VALUES ('" + list.get(i).getName() + "');");
                    Toast.makeText(context, "Nouvelle allergie sauvegardée", Toast.LENGTH_LONG).show();
                }else{
                    //Not check
                    db.execSQL("DELETE FROM allergy WHERE name = '" + list.get(i).getName() +"';");
                    Toast.makeText(context, "Allergie supprimée", Toast.LENGTH_LONG).show();
                }
                db.close();
            }
        });
        return view;
    }
}
