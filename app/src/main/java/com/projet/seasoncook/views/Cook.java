package com.projet.seasoncook.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projet.seasoncook.R;
import com.projet.seasoncook.models.Etape;
import com.projet.seasoncook.models.Ingredient;
import com.projet.seasoncook.models.Recette;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class Cook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);

        Recette recette = (Recette) getIntent().getSerializableExtra("cook");

        ((TextView) findViewById(R.id.titleCook)).setText(recette.getTitle());
        ((TextView) findViewById(R.id.timePrep)).setText(getText(R.string.preparation).toString() + recette.getTimePreparation() + " min");
        ((TextView) findViewById(R.id.timeCook)).setText(getText(R.string.cuisson).toString() + recette.getTimeCooking() + " min");
        ((TextView) findViewById(R.id.portion)).setText(recette.getPortion() + " " + getText(R.string.personne).toString());

        ImageView icon = (ImageView) findViewById(R.id.iconCook);
        int res = getResources().getIdentifier(recette.getImageLink(), "drawable", getPackageName());
        icon.setImageDrawable(getResources().getDrawable(res));

        GridLayout grid = (GridLayout) findViewById(R.id.ingList);
        grid.setRowCount(grid.getColumnCount() == 1 ? recette.getIngredients().size() : recette.getIngredients().size()/2);
        for(Map.Entry<Ingredient, Integer> ing : recette.getIngredients().entrySet()){
            TextView text = new TextView(this);
            text.setText(ing.getKey().getName() + ": " + ing.getValue() + " " + ing.getKey().getUnity().getSymbole());
            text.setTextSize(18f);

            GridLayout.Spec colspan = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f);
            GridLayout.Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1f);
            GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(rowSpan, colspan);
            grid.addView(text, gridParam);
        }

        LinearLayout steps = (LinearLayout) findViewById(R.id.stepList);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for(Etape step : recette.getSteps()){
            TextView titleStep = new TextView(this);
            titleStep.setText(getText(R.string.step).toString() + step.getNumber());
            titleStep.setTextSize(20f);
            titleStep.setTypeface(Typeface.DEFAULT_BOLD);
            params.setMargins(0, 20, 0, 0);
            titleStep.setLayoutParams(params);
            steps.addView(titleStep);

            TextView stepDesc = new TextView(this);
            stepDesc.setText(step.getDescription());
            params.setMargins(0, 0, 0, 20);
            stepDesc.setTextSize(18f);
            stepDesc.setLayoutParams(params);
            steps.addView(stepDesc);
        }

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
            Intent intent = new Intent(Cook.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else if(item.getItemId() == R.id.back){
            super.onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}