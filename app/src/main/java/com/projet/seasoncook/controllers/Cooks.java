package com.projet.seasoncook.controllers;

import static java.util.stream.Collectors.toCollection;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.projet.seasoncook.R;
import com.projet.seasoncook.database.DatabaseHelper;
import com.projet.seasoncook.models.CookType;
import com.projet.seasoncook.models.Etape;
import com.projet.seasoncook.models.Ingredient;
import com.projet.seasoncook.models.IngredientUnity;
import com.projet.seasoncook.models.Recette;
import com.projet.seasoncook.models.Seasons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cooks {

    private static Cooks instance;
    private List<Recette> recettes;
    private List<Ingredient> allergy;
    private Map<Integer, Ingredient> ingredients;
    private Context context;

    private Cooks(Context context){
        this.recettes = new ArrayList<Recette>();
        this.ingredients = new HashMap<Integer, Ingredient>();
        this.allergy = new ArrayList<>();
        this.context = context;
        initCooks();
    }

    public static synchronized Cooks getInstance(Context context){
        if(instance == null){
            instance = new Cooks(context);
        }
        return instance;
    }

    public List<Recette> getCooks(){
        //All ingredient in database
        this.allergy.clear();
        DatabaseHelper dbHelper = new DatabaseHelper(this.context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("allergy", new String[] {"name"}, "", new String[] {}, null, null, null);
        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                for(int i = 0; i < this.ingredients.size(); i++){
                    if(this.ingredients.get(i) != null && this.ingredients.get(i).getName().equals(name)){
                        this.allergy.add(this.ingredients.get(i));
                    }
                }
            }while (cursor.moveToNext());
        }


        List<Recette> cooks = new ArrayList<>();
        //toutes les recettes
        for (Recette recette : this.recettes) {
            boolean contientAllergene = false;
            // Tous les incrédients de la rectte
            for (Ingredient ingredient : recette.getListIngredients()) {
                //Si l'ingrédient est dans la liste des allergène
                if (this.allergy.contains(ingredient)) {
                    contientAllergene = true;
                    break;
                }
            }
            if (!contientAllergene) {
                cooks.add(recette);
            }else{
            }
        }
        return cooks;
    }

    public List<Ingredient> getIngredientsFiltre(CharSequence filtre){
        return getIngredients().stream().filter((e) -> e.getName().contains(filtre)).collect(Collectors.toList());
    }

    public List<Ingredient> getIngredients(){
        return new ArrayList<>(new ArrayList<>(this.ingredients.values())).stream().sorted(Comparator.comparing(Ingredient::getName)).collect(Collectors.toList());
    }

    public List<Recette> cooksFilter(Seasons seasons, CookType type){
        return getCooks().stream().filter(e -> Arrays.asList(e.getSeasons()).contains(seasons)).filter(e -> e.getType().equals(type)).collect(Collectors.toList());
    }

    private void initCooks(){

        this.ingredients.put(1, new Ingredient(context.getString(R.string.ing_PS), IngredientUnity.Aucune));
        this.ingredients.put(2, new Ingredient(context.getString(R.string.ing_RH), IngredientUnity.Gramme));
        this.ingredients.put(3, new Ingredient(context.getString(R.string.ing_OE), IngredientUnity.Aucune));
        this.ingredients.put(4, new Ingredient(context.getString(R.string.ing_CR), IngredientUnity.CentiLitre));
        this.ingredients.put(5, new Ingredient(context.getString(R.string.ing_PA), IngredientUnity.Cuillere_soupe));
        this.ingredients.put(6, new Ingredient(context.getString(R.string.ing_SP), IngredientUnity.Gramme));
        this.ingredients.put(7, new Ingredient(context.getString(R.string.ing_CA), IngredientUnity.Gramme));
        this.ingredients.put(8, new Ingredient(context.getString(R.string.ing_PO), IngredientUnity.Aucune));
        this.ingredients.put(9, new Ingredient(context.getString(R.string.ing_PC), IngredientUnity.Gramme));
        this.ingredients.put(10, new Ingredient(context.getString(R.string.ing_BL), IngredientUnity.Aucune));
        this.ingredients.put(11, new Ingredient(context.getString(R.string.ing_BR), IngredientUnity.Gramme));
        this.ingredients.put(12, new Ingredient(context.getString(R.string.ing_FR), IngredientUnity.Gramme));
        this.ingredients.put(13, new Ingredient(context.getString(R.string.ing_TV), IngredientUnity.Cuillere_cafe));
        this.ingredients.put(14, new Ingredient(context.getString(R.string.ing_EF), IngredientUnity.CentiLitre));
        this.ingredients.put(15, new Ingredient(context.getString(R.string.ing_OR), IngredientUnity.Aucune));
        this.ingredients.put(16, new Ingredient(context.getString(R.string.ing_SA), IngredientUnity.Cuillere_soupe));
        this.ingredients.put(17, new Ingredient(context.getString(R.string.ing_CO), IngredientUnity.Aucune));
        this.ingredients.put(18, new Ingredient(context.getString(R.string.ing_AU), IngredientUnity.Aucune));
        this.ingredients.put(19, new Ingredient(context.getString(R.string.ing_PV), IngredientUnity.Aucune));
        this.ingredients.put(20, new Ingredient(context.getString(R.string.ing_PR), IngredientUnity.Aucune));
        this.ingredients.put(21, new Ingredient(context.getString(R.string.ing_TO), IngredientUnity.Aucune));
        this.ingredients.put(22, new Ingredient(context.getString(R.string.ing_OI), IngredientUnity.Aucune));
        this.ingredients.put(23, new Ingredient(context.getString(R.string.ing_AI), IngredientUnity.Gousse));
        this.ingredients.put(24, new Ingredient(context.getString(R.string.ing_EA), IngredientUnity.Gramme));
        this.ingredients.put(25, new Ingredient(context.getString(R.string.ing_PP), IngredientUnity.Cuillere_soupe));
        this.ingredients.put(26, new Ingredient(context.getString(R.string.ing_RB), IngredientUnity.Cuillere_soupe));
        this.ingredients.put(27, new Ingredient(context.getString(R.string.ing_HO), IngredientUnity.Cuillere_soupe));
        this.ingredients.put(28, new Ingredient(context.getString(R.string.ing_CP), IngredientUnity.Aucune));
        this.ingredients.put(29, new Ingredient(context.getString(R.string.ing_PT), IngredientUnity.Gramme));
        this.ingredients.put(30, new Ingredient(context.getString(R.string.ing_CAR), IngredientUnity.Gramme));
        this.ingredients.put(31, new Ingredient(context.getString(R.string.ing_PN), IngredientUnity.Gramme));
        this.ingredients.put(32, new Ingredient(context.getString(R.string.ing_BV), IngredientUnity.CentiLitre));
        this.ingredients.put(33, new Ingredient(context.getString(R.string.ing_POI), IngredientUnity.Gramme));
        this.ingredients.put(34, new Ingredient(context.getString(R.string.ing_NM), IngredientUnity.Gramme));
        this.ingredients.put(35, new Ingredient(context.getString(R.string.ing_CRA), IngredientUnity.Quartier));
        this.ingredients.put(36, new Ingredient(context.getString(R.string.ing_PG), IngredientUnity.Aucune));
        this.ingredients.put(37, new Ingredient(context.getString(R.string.ing_JC), IngredientUnity.Cuillere_soupe));
        this.ingredients.put(38, new Ingredient(context.getString(R.string.ing_MT), IngredientUnity.Cuillere_soupe));
        this.ingredients.put(39, new Ingredient(context.getString(R.string.ing_FS), IngredientUnity.Gramme));
        this.ingredients.put(40, new Ingredient(context.getString(R.string.ing_MG), IngredientUnity.Aucune));
        this.ingredients.put(41, new Ingredient(context.getString(R.string.ing_FP), IngredientUnity.Aucune));
        this.ingredients.put(42, new Ingredient(context.getString(R.string.ing_CI), IngredientUnity.Aucune));
        this.ingredients.put(43, new Ingredient(context.getString(R.string.ing_CIB), IngredientUnity.Brins));
        this.ingredients.put(44, new Ingredient(context.getString(R.string.ing_AV), IngredientUnity.Aucune));
        this.ingredients.put(45, new Ingredient(context.getString(R.string.ing_VB), IngredientUnity.CentiLitre));


        //Ajout de desserts
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(this.ingredients.get(1), 1);
            ing.put(this.ingredients.get(2), 800);
            ing.put(this.ingredients.get(3), 3);
            ing.put(this.ingredients.get(4), 15);
            ing.put(this.ingredients.get(5), 2);
            ing.put(this.ingredients.get(6), 100);
            ing.put(this.ingredients.get(7), 100);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, context.getString(R.string.st_C1S1)),
                    new Etape(2, context.getString(R.string.st_C1S2)),
                    new Etape(3, context.getString(R.string.st_C1S3)),
                    new Etape(4, context.getString(R.string.st_C1S4)),
                    new Etape(5, context.getString(R.string.st_C1S5)),
                    new Etape(6, context.getString(R.string.st_C1S6))
            );

            this.recettes.add(new Recette( context.getString(R.string.C1), steps, ing, 25, 25, "tarte_rhubarbe", CookType.dessert, new Seasons[] {Seasons.Spring, Seasons.Summer}, 6));
        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(this.ingredients.get(8), 4);
            ing.put(this.ingredients.get(9), 100);
            ing.put(this.ingredients.get(10), 1);
            ing.put(this.ingredients.get(11), 50);
            ing.put(this.ingredients.get(12), 60);
            ing.put(this.ingredients.get(6), 50);
            ing.put(this.ingredients.get(3), 3);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, context.getString(R.string.st_C2S1)),
                    new Etape(2, context.getString(R.string.st_C2S2)),
                    new Etape(3, context.getString(R.string.st_C2S3)),
                    new Etape(4, context.getString(R.string.st_C2S4)),
                    new Etape(5, context.getString(R.string.st_C2S5)),
                    new Etape(6, context.getString(R.string.st_C2S6)),
                    new Etape(7, context.getString(R.string.st_C2S7)),
                    new Etape(8, context.getString(R.string.st_C2S8)),
                    new Etape(9, context.getString(R.string.st_C2S9)),
                    new Etape(10, context.getString(R.string.st_C2S10))
            );

            this.recettes.add(new Recette(context.getString(R.string.C2), steps, ing, 30, 45, "clafoutis_poire", CookType.dessert, new Seasons[]{Seasons.Fall}, 4));
        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(this.ingredients.get(13), 3);
            ing.put(this.ingredients.get(14), 15);
            ing.put(this.ingredients.get(15), 9);
            ing.put(this.ingredients.get(16), 3);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, context.getString(R.string.st_C3S1)),
                    new Etape(2, context.getString(R.string.st_C3S2)),
                    new Etape(3, context.getString(R.string.st_C3S3))
            );

            this.recettes.add(new Recette(context.getString(R.string.C3), steps, ing, 15, 0, "salade_orange", CookType.dessert, new Seasons[] {Seasons.Winter}, 6));
        }

        //Ajout de plats
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(this.ingredients.get(17), 2);
            ing.put(this.ingredients.get(18), 1);
            ing.put(this.ingredients.get(19), 1);
            ing.put(this.ingredients.get(20), 1);
            ing.put(this.ingredients.get(21), 3);
            ing.put(this.ingredients.get(22), 1);
            ing.put(this.ingredients.get(23), 2);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, context.getString(R.string.st_C4S1)),
                    new Etape(2, context.getString(R.string.st_C4S2)),
                    new Etape(3, context.getString(R.string.st_C4S3)),
                    new Etape(4, context.getString(R.string.st_C4S4)),
                    new Etape(5, context.getString(R.string.st_C4S5))
            );

            this.recettes.add(new Recette(context.getString(R.string.C4), steps, ing, 20, 60, "ratatouille", CookType.main_course, new Seasons[] {Seasons.Spring, Seasons.Summer}, 4));
        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(this.ingredients.get(21), 6);
            ing.put(this.ingredients.get(24), 600);
            ing.put(this.ingredients.get(22), 2);
            ing.put(this.ingredients.get(25), 3);
            ing.put(this.ingredients.get(26), 2);
            ing.put(this.ingredients.get(27), 4);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, context.getString(R.string.st_C5S1)),
                    new Etape(2, context.getString(R.string.st_C5S2)),
                    new Etape(3, context.getString(R.string.st_C5S3)),
                    new Etape(4, context.getString(R.string.st_C5S4)),
                    new Etape(5, context.getString(R.string.st_C5S5)),
                    new Etape(6, context.getString(R.string.st_C5S6)),
                    new Etape(7, context.getString(R.string.st_C5S7)),
                    new Etape(8, context.getString(R.string.st_C5S8)),
                    new Etape(9, context.getString(R.string.st_C5S9))

            );

            this.recettes.add(new Recette(context.getString(R.string.C5), steps, ing, 30, 60, "tomate_farcie", CookType.main_course, new Seasons[] {Seasons.Summer, Seasons.Fall}, 6));
        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(this.ingredients.get(28), 6);
            ing.put(this.ingredients.get(29), 500);
            ing.put(this.ingredients.get(30), 500);
            ing.put(this.ingredients.get(31), 500);
            ing.put(this.ingredients.get(27), 3);
            ing.put(this.ingredients.get(32), 25);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, context.getString(R.string.st_C6S1)),
                    new Etape(2, context.getString(R.string.st_C6S2)),
                    new Etape(3, context.getString(R.string.st_C6S3))

            );

            this.recettes.add(new Recette(context.getString(R.string.C6), steps, ing, 20, 45, "poulet_roti", CookType.main_course, new Seasons[] {Seasons.Fall}, 6));
        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(this.ingredients.get(33), 600);
            ing.put(this.ingredients.get(12), 150);
            ing.put(this.ingredients.get(3), 100);
            ing.put(this.ingredients.get(34), 1);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, context.getString(R.string.st_C7S1)),
                    new Etape(2, context.getString(R.string.st_C7S2)),
                    new Etape(3, context.getString(R.string.st_C7S3))

            );

            this.recettes.add(new Recette(context.getString(R.string.C7), steps, ing, 20, 25, "croquette_poireau", CookType.main_course, new Seasons[] {Seasons.Winter}, 4));
        }

        //Ajout entrées
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(this.ingredients.get(35), 1);
            ing.put(this.ingredients.get(36), 2);
            ing.put(this.ingredients.get(30), 200);
            ing.put(this.ingredients.get(37), 3);
            ing.put(this.ingredients.get(38), 1);
            ing.put(this.ingredients.get(3), 1);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, context.getString(R.string.st_C8S1)),
                    new Etape(2, context.getString(R.string.st_C8S2)),
                    new Etape(3, context.getString(R.string.st_C8S3)),
                    new Etape(4, context.getString(R.string.st_C8S4)),
                    new Etape(5, context.getString(R.string.st_C8S5))

            );

            this.recettes.add(new Recette(context.getString(R.string.C8), steps, ing, 10, 2, "remoulade", CookType.starter, new Seasons[] {Seasons.Summer, Seasons.Fall}, 4));
        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(this.ingredients.get(39), 400);
            ing.put(this.ingredients.get(40), 1);
            ing.put(this.ingredients.get(36), 1);
            ing.put(this.ingredients.get(41), 2);
            ing.put(this.ingredients.get(27), 3);
            ing.put(this.ingredients.get(42), 1);
            ing.put(this.ingredients.get(43), 12);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, context.getString(R.string.st_C9S1)),
                    new Etape(2, context.getString(R.string.st_C9S2)),
                    new Etape(3, context.getString(R.string.st_C9S3)),
                    new Etape(4, context.getString(R.string.st_C9S4)),
                    new Etape(5, context.getString(R.string.st_C9S5))

            );

            this.recettes.add(new Recette(context.getString(R.string.C9), steps, ing, 30, 0, "tartare_saumon", CookType.starter, new Seasons[] {Seasons.Summer, Seasons.Spring, Seasons.Winter}, 4));

        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(this.ingredients.get(44), 12);
            ing.put(this.ingredients.get(27), 3);
            ing.put(this.ingredients.get(22), 3);
            ing.put(this.ingredients.get(30), 2);
            ing.put(this.ingredients.get(20), 1);
            ing.put(this.ingredients.get(45), 30);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, context.getString(R.string.st_C10S1)),
                    new Etape(2, context.getString(R.string.st_C10S2)),
                    new Etape(3, context.getString(R.string.st_C10S3)),
                    new Etape(4, context.getString(R.string.st_C10S4)),
                    new Etape(5, context.getString(R.string.st_C10S5))

            );

            this.recettes.add(new Recette(context.getString(R.string.C10), steps, ing, 20, 90, "artichauts_poivrade", CookType.starter, new Seasons[] {Seasons.Summer, Seasons.Spring, Seasons.Winter}, 6));

        }


    }
}
