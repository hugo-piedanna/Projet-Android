package com.projet.seasoncook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.TextView;

import com.projet.seasoncook.controllers.Cooks;
import com.projet.seasoncook.models.CookType;
import com.projet.seasoncook.models.ListAdapter;
import com.projet.seasoncook.models.Seasons;

import java.util.stream.Collectors;

public class chooseCook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_cook);

        Seasons seasons = Seasons.valueOf(getIntent().getStringExtra("season"));
        CookType type = CookType.valueOf(getIntent().getStringExtra("cooktype"));

        ListView list = findViewById(R.id.listCook);
        ListAdapter adapter = new ListAdapter(Cooks.getInstance().cooksFilter(seasons, type), getApplicationContext());

        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}