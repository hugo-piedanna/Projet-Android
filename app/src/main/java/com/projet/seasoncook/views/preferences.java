package com.projet.seasoncook.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.projet.seasoncook.R;
import com.projet.seasoncook.controllers.Cooks;
import com.projet.seasoncook.models.Ingredient;
import com.projet.seasoncook.models.ListAdapterIngredients;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class preferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        ListView list = (ListView) findViewById(R.id.listIngredientsPref);
        ListAdapterIngredients adapter = new ListAdapterIngredients(Cooks.getInstance().getIngredients(), getApplicationContext());
        list.setAdapter(adapter);

        EditText search = (EditText) findViewById(R.id.searchBar);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<Ingredient> ings = Cooks.getInstance().getIngredients();
                if(charSequence.length() != 0) {
                    ings = Cooks.getInstance().getIngredientsFiltre(charSequence);
                }
                ListAdapterIngredients adapter = new ListAdapterIngredients(ings, getApplicationContext());
                list.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item){
        if(item.getItemId() == R.id.home){
            Intent intent = new Intent(preferences.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else if(item.getItemId() == R.id.back){
            super.onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}