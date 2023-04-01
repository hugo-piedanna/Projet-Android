package com.projet.seasoncook.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.projet.seasoncook.R;
import com.projet.seasoncook.controllers.Cooks;
import com.projet.seasoncook.models.CookType;
import com.projet.seasoncook.models.ListAdapter;
import com.projet.seasoncook.models.Recette;
import com.projet.seasoncook.models.Seasons;

import org.jetbrains.annotations.NotNull;

public class chooseCook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_cook);

        Seasons seasons = Seasons.valueOf(getIntent().getStringExtra("season"));
        CookType type = CookType.valueOf(getIntent().getStringExtra("cooktype"));

        ListView list = findViewById(R.id.listCook);
        ListAdapter adapter = new ListAdapter(Cooks.getInstance(getApplicationContext()).cooksFilter(seasons, type), getApplicationContext());

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Recette recette = (Recette) adapterView.getItemAtPosition(position);

                Intent intent = new Intent(chooseCook.this, Cook.class);
                intent.putExtra("cook", recette);
                startActivity(intent);

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
            Intent intent = new Intent(chooseCook.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else if(item.getItemId() == R.id.back){
            super.onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}