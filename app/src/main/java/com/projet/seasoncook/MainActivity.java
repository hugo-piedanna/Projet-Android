package com.projet.seasoncook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.projet.seasoncook.database.DatabaseHelper;
import com.projet.seasoncook.models.CookType;
import com.projet.seasoncook.models.Etape;
import com.projet.seasoncook.models.Ingredient;
import com.projet.seasoncook.models.IngredientUnity;
import com.projet.seasoncook.models.Recette;
import com.projet.seasoncook.models.Seasons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<Recette> recettes;
    private String params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recettes = new ArrayList<>();
        initRecette();
        setParams();

        RadioGroup rGroup = (RadioGroup)findViewById(R.id.choose_season);
        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                String season = "";
                for(Seasons s : Seasons.values()){
                    if(s.getId() == checkedId){
                        season = s.name();
                    }
                }
                
                ContentValues value = new ContentValues();
                value.put("season", season);
                db.update("settings", value, " id = 1", null);

                db.close();
            }
        });

    }

    public void setParams(){
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("settings", new String[] {"season"}, "", new String[] {}, null, null, null);
        if(cursor.moveToFirst()){
            this.params = cursor.getString(cursor.getColumnIndexOrThrow("season"));
            ((RadioButton)findViewById(Seasons.valueOf(this.params).getId())).setChecked(true);
        }
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void initRecette(){
        Map<Integer, Ingredient> ingredients = new HashMap<Integer, Ingredient>();
        ingredients.put(1, new Ingredient("pâte sablée", IngredientUnity.Aucune));
        ingredients.put(2, new Ingredient("rhubarbe", IngredientUnity.Gramme));
        ingredients.put(3, new Ingredient("oeuf", IngredientUnity.Aucune));
        ingredients.put(4, new Ingredient("crème", IngredientUnity.CentiLitre));
        ingredients.put(5, new Ingredient("poudre d'amandes", IngredientUnity.Cuillere_soupe));
        ingredients.put(6, new Ingredient("sucre en poudre", IngredientUnity.Gramme));
        ingredients.put(7, new Ingredient("cassonade", IngredientUnity.Gramme));
        ingredients.put(8, new Ingredient("poire", IngredientUnity.Aucune));
        ingredients.put(9, new Ingredient("pépites de chocolat", IngredientUnity.Gramme));
        ingredients.put(10, new Ingredient("boîte de lait non sucré", IngredientUnity.Aucune));
        ingredients.put(11, new Ingredient("beurre", IngredientUnity.Gramme));
        ingredients.put(12, new Ingredient("farine", IngredientUnity.Gramme));
        ingredients.put(13, new Ingredient("thé vert", IngredientUnity.Cuillere_cafe));
        ingredients.put(14, new Ingredient("eau frémissante", IngredientUnity.CentiLitre));
        ingredients.put(15, new Ingredient("orange", IngredientUnity.Aucune));
        ingredients.put(16, new Ingredient("sirop d'agave", IngredientUnity.Cuillere_soupe));
        ingredients.put(17, new Ingredient("courgette", IngredientUnity.Aucune));
        ingredients.put(18, new Ingredient("aubergine", IngredientUnity.Aucune));
        ingredients.put(19, new Ingredient("poivron vert", IngredientUnity.Aucune));
        ingredients.put(20, new Ingredient("poivron rouge", IngredientUnity.Aucune));
        ingredients.put(21, new Ingredient("tomate", IngredientUnity.Aucune));
        ingredients.put(22, new Ingredient("oignon", IngredientUnity.Aucune));
        ingredients.put(23, new Ingredient("ail", IngredientUnity.Gousse));
        ingredients.put(24, new Ingredient("épaules d'agneau", IngredientUnity.Gramme));
        ingredients.put(25, new Ingredient("pignons de pin", IngredientUnity.Cuillere_soupe));
        ingredients.put(26, new Ingredient("raisin sec blond", IngredientUnity.Cuillere_soupe));
        ingredients.put(27, new Ingredient("huilde d'olive", IngredientUnity.Cuillere_soupe));
        ingredients.put(28, new Ingredient("cuisse de poulet", IngredientUnity.Aucune));
        ingredients.put(29, new Ingredient("pomme de terre", IngredientUnity.Gramme));
        ingredients.put(30, new Ingredient("carotte", IngredientUnity.Gramme));
        ingredients.put(31, new Ingredient("panais", IngredientUnity.Gramme));
        ingredients.put(32, new Ingredient("bouillon de volaille", IngredientUnity.CentiLitre));
        ingredients.put(33, new Ingredient("poireaux", IngredientUnity.Gramme));
        ingredients.put(34, new Ingredient("noix de muscade", IngredientUnity.Gramme));
        ingredients.put(35, new Ingredient("céleri-rave", IngredientUnity.Quartier));
        ingredients.put(36, new Ingredient("pomme granny", IngredientUnity.Aucune));
        ingredients.put(37, new Ingredient("jus de citron", IngredientUnity.Cuillere_soupe));
        ingredients.put(38, new Ingredient("moutarde", IngredientUnity.Cuillere_soupe));
        ingredients.put(39, new Ingredient("filet de saumon", IngredientUnity.Gramme));
        ingredients.put(40, new Ingredient("mangue", IngredientUnity.Aucune));
        ingredients.put(41, new Ingredient("fruit de la passion", IngredientUnity.Aucune));
        ingredients.put(42, new Ingredient("citron vert", IngredientUnity.Aucune));
        ingredients.put(43, new Ingredient("ciboulette", IngredientUnity.Brins));
        ingredients.put(44, new Ingredient("artichaut violet", IngredientUnity.Aucune));
        ingredients.put(45, new Ingredient("vin blanc", IngredientUnity.CentiLitre));


        //Ajout de desserts
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(ingredients.get(1), 1);
            ing.put(ingredients.get(2), 800);
            ing.put(ingredients.get(3), 3);
            ing.put(ingredients.get(4), 15);
            ing.put(ingredients.get(5), 2);
            ing.put(ingredients.get(6), 100);
            ing.put(ingredients.get(7), 100);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, "Epluchez et coupez la rhubarbe en tronçons. Mettez-les dans un saladier. Saupoudrez de sucre en poudre et laissez reposer pendant 2 h, en remuant régulièrement. "),
                    new Etape(2, "Etalez la pâte sablée dans un moule à tarte beurré. Piquez le fond à la fourchette. Placez 30 mn au frais. "),
                    new Etape(3, "Préchauffez le four à th 6 (180° C). "),
                    new Etape(4, "Couvrez le fond de pâte de papier sulfurisé et garnissez-le de légumes secs. Enfournez pendant 15 mn."),
                    new Etape(5, "Egouttez la rhubarbe. Mélangez l’œuf entier et les jaunes dans un saladier avec la crème liquide, et la cassonade. "),
                    new Etape(6, "Retirez les légumes secs et le papier cuisson. Saupoudrez le fond de tarte de poudre d’amandes. Ajoutez la préparation à la crème puis couvrez de rhubarbe. Enfournez pendant 25 mn. Retirez du four et laissez refroidir sur une grille. ")
            );

            this.recettes.add(new Recette("Tarte à la rhubarbe, à la cassonade et aux amandes", steps, ing, 25, 25, "", CookType.dessert, new Seasons[] {Seasons.Spring, Seasons.Summer}, 6));
        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(ingredients.get(8), 4);
            ing.put(ingredients.get(9), 100);
            ing.put(ingredients.get(10), 1);
            ing.put(ingredients.get(11), 50);
            ing.put(ingredients.get(12), 60);
            ing.put(ingredients.get(6), 50);
            ing.put(ingredients.get(3), 3);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, "Eplucher et tailler en morceaux les poires."),
                    new Etape(2, "Préchauffer le four à 180°C (th.6)."),
                    new Etape(3, "Verser le lait concentré dans une casserole, le porter à petites ébullitions."),
                    new Etape(4, "Dans une autre casserole, déposer 40 g de beurre, faire fondre à feu doux."),
                    new Etape(5, "Dans un saladier, mélanger la farine et le sucre, verser le lait concentré."),
                    new Etape(6, "Ajouter les œufs, mélanger et incorporer le beurre fondu."),
                    new Etape(7, "Beurrer un plat à four, y répartir les morceaux de poires et les pépites de chocolat, verser la pâte."),
                    new Etape(8, "Enfourner 40 à 45 minutes (le temps de cuisson varie de quelques minutes selon les fours)."),
                    new Etape(9, "Vérifier la cuisson en enfonçant la lame du couteau dans le clafoutis : la lame doit en ressortir bien propre."),
                    new Etape(10, "Déguster le clafoutis tiède ou froid.")
            );

            this.recettes.add(new Recette("Clafoutis aux poires et chocolat", steps, ing, 30, 45, "", CookType.dessert, new Seasons[]{Seasons.Fall}, 4));
        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(ingredients.get(13), 3);
            ing.put(ingredients.get(14), 15);
            ing.put(ingredients.get(15), 9);
            ing.put(ingredients.get(16), 3);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, "Faites bouillir de l’eau. Mettez le thé dans un bol, versez l’eau frémissante dessus, couvrez et laissez infuser 5 minutes."),
                    new Etape(2, "Pelez 6 oranges à vif, coupez-les en fines tranches et mettez-les dans un saladier."),
                    new Etape(3, "Filtrez le thé infusé, dans un autre bol, ajoutez le jus des 3 oranges restantes et le sirop d’agave ou l’édulcorant à la stevia, mélangez bien et versez sur les rondelles d’oranges. Ajoutez une pincée de feuilles de thé vert et réservez au frais jusqu’au moment de servir.")
            );

            this.recettes.add(new Recette("Salade d’orange au thé vert", steps, ing, 15, 0, "", CookType.dessert, new Seasons[] {Seasons.Winter}, 6));
        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(ingredients.get(1), 1);
            ing.put(ingredients.get(2), 800);
            ing.put(ingredients.get(3), 3);
            ing.put(ingredients.get(4), 15);
            ing.put(ingredients.get(5), 2);
            ing.put(ingredients.get(6), 100);
            ing.put(ingredients.get(7), 100);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, "Epluchez et coupez la rhubarbe en tronçons. Mettez-les dans un saladier. Saupoudrez de sucre en poudre et laissez reposer pendant 2 h, en remuant régulièrement. "),
                    new Etape(2, "Etalez la pâte sablée dans un moule à tarte beurré. Piquez le fond à la fourchette. Placez 30 mn au frais. "),
                    new Etape(3, "Préchauffez le four à th 6 (180° C). "),
                    new Etape(4, "Couvrez le fond de pâte de papier sulfurisé et garnissez-le de légumes secs. Enfournez pendant 15 mn."),
                    new Etape(5, "Egouttez la rhubarbe. Mélangez l’œuf entier et les jaunes dans un saladier avec la crème liquide, et la cassonade. "),
                    new Etape(6, "Retirez les légumes secs et le papier cuisson. Saupoudrez le fond de tarte de poudre d’amandes. Ajoutez la préparation à la crème puis couvrez de rhubarbe. Enfournez pendant 25 mn. Retirez du four et laissez refroidir sur une grille. ")
            );

            this.recettes.add(new Recette("Tarte à la rhubarbe, à la cassonade et aux amandes", steps, ing, 25, 25, "", CookType.dessert, new Seasons[] {Seasons.Spring, Seasons.Summer}, 6));
        }

        //Ajout de plats
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(ingredients.get(17), 2);
            ing.put(ingredients.get(18), 1);
            ing.put(ingredients.get(19), 1);
            ing.put(ingredients.get(20), 1);
            ing.put(ingredients.get(21), 3);
            ing.put(ingredients.get(22), 1);
            ing.put(ingredients.get(23), 2);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, "Lavez et détaillez les courgettes, l'aubergine, le poivron vert et le rouge, en cubes de taille moyenne. Coupez les tomates en quartiers et émincez l'oignon."),
                    new Etape(2, "Dans une poêle, versez un peu d'huile d'olive et faites-y revenir les uns après les autres les différents légumes pendant 5 minutes pour qu'ils colorent. Commencez par les poivrons, puis les aubergines, les courgettes et enfin les oignons et les tomates que vous cuirez ensemble."),
                    new Etape(3, "Après avoir fait cuire les légumes, ajoutez-les tous aux tomates et aux oignons, baissez le feu puis mélangez. Ajoutez un beau bouquet garni de thym, de romarin et de laurier, salez, poivrez, puis couvrez pour laisser mijoter 40 minutes en remuant régulièrement."),
                    new Etape(4, "À environ 10 minutes du terme de la cuisson, ajoutez les deux belles gousses d'ail écrasées puis couvrez de nouveau. N'hésitez pas à goûter et à assaisonner de nouveau selon vos goûts."),
                    new Etape(5, "Dégustez avec des grillades ou un barbecue.")
            );

            this.recettes.add(new Recette("Ratatouille", steps, ing, 20, 60, "", CookType.main_course, new Seasons[] {Seasons.Spring, Seasons.Summer}, 4));
        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(ingredients.get(21), 6);
            ing.put(ingredients.get(24), 600);
            ing.put(ingredients.get(22), 2);
            ing.put(ingredients.get(25), 3);
            ing.put(ingredients.get(26), 2);
            ing.put(ingredients.get(27), 4);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, "Coupez la viande en tout petits dés. "),
                    new Etape(2, "Pelez et émincez très finement les oignons. "),
                    new Etape(3, "Faites chauffer l’huile dans une cocotte. Faites-y revenir les oignons avec les dés de viande en remuant. Ajoutez les pignons de pin et les raisins secs. Salez et poivrez. Versez un petit verre d’eau. Couvrez. Faites cuire à feu très doux pendant 15 minutes en remuant régulièrement."),
                    new Etape(4, "Préchauffez le four th 7 (210°C)."),
                    new Etape(5, "Lavez les tomates et coupez les chapeaux. Evidez-les délicatement avec une petite cuillère."),
                    new Etape(6, "Posez les coques vides de tomates dans un plat à four huilé. Farcissez-les de la préparation à la viande, arrosez d’un filet d’huile d’olive, salez et poivrez. "),
                    new Etape(7, "Enfournez et faites cuire 45 minutes."),
                    new Etape(8, "Quinze minutes avant la fin de la cuisson, posez les chapeaux des tomates dessus. Poursuivez la cuisson quinze minutes."),
                    new Etape(9, "Servez dès la sortie du four. ")

            );

            this.recettes.add(new Recette("Tomates farcies à l’agneau", steps, ing, 30, 60, "", CookType.main_course, new Seasons[] {Seasons.Summer, Seasons.Fall}, 6));
        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(ingredients.get(28), 6);
            ing.put(ingredients.get(29), 500);
            ing.put(ingredients.get(30), 500);
            ing.put(ingredients.get(31), 500);
            ing.put(ingredients.get(27), 3);
            ing.put(ingredients.get(32), 25);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, "Préchauffez le four à 200°C (thermostat 6/7). Pelez les pommes de terre, les panais et les carottes et émincez-les. Coupez en deux dans la largeur la tête d’ail."),
                    new Etape(2, "Huilez un plat à four et placez-y les légumes. Ajoutez les têtes d’ail. Badigeonnez les cuisses de poulet d’huile et placez-les sur les légumes. Parsemez de thym. Versez le bouillon de légumes dans le fond du plat. Salez et donnez un tour de moulin à poivre."),
                    new Etape(3, "Enfournez et faites rôtir 45 minutes. Arrosez le poulet régulièrement du jus de cuisson. Ajoutez un peu d’eau si besoin dans le fond du plat à mi-cuisson. Servez chaud.")

            );

            this.recettes.add(new Recette("Poulet et légumes rôtis à l’ail", steps, ing, 20, 45, "", CookType.main_course, new Seasons[] {Seasons.Fall}, 6));
        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(ingredients.get(33), 600);
            ing.put(ingredients.get(12), 150);
            ing.put(ingredients.get(3), 100);
            ing.put(ingredients.get(34), 1);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, "Faire bouillir de l'eau salée dans une casserole. Parer les poireaux afin de ne conserver que le vert tendre et le blanc. Découper les poireaux en grosses rondelles puis plonger celles-ci dans l'eau bouillante durant 5 minutes. Egoutter puis sécher les poireaux. Hacher ceux-ci. "),
                    new Etape(2, "Dans un saladier, battre les oeufs. Ajouter la farine, le thym et la muscade puis mélanger. Saler et poivrer. Travailler la pâte à l'aide d'une spatule. Ajouter les poireaux hachés puis remuer. "),
                    new Etape(3, "Faire chauffer de l'huile dans le fond d'un wok. A l'aide d'une cuillère à soupe, prélever de la pâte et plonger les croquettes dans l'huile bouillante. Faire dorer chaque croquette avant de les égoutter sur du papier absorbant. ")

            );

            this.recettes.add(new Recette("Croquettes de poireaux", steps, ing, 20, 25, "", CookType.main_course, new Seasons[] {Seasons.Winter}, 4));
        }

        //Ajout entrées
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(ingredients.get(35), 1);
            ing.put(ingredients.get(36), 2);
            ing.put(ingredients.get(30), 200);
            ing.put(ingredients.get(37), 3);
            ing.put(ingredients.get(38), 1);
            ing.put(ingredients.get(3), 1);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, "Epluchez et râpez le céleri avec une grosse râpe. Faites-le blanchir 2 minutes dans l’eau bouillante salée. Egouttez et rafraîchissez-le sous l’eau froide. Laissez-le égoutter."),
                    new Etape(2, "Pelez et râpez les carottes avec une grosse râpe. Pelez et râpez les pommes avec une grosse râpe. Arrosez les pommes râpées de jus de citron et mélangez. "),
                    new Etape(3, "Mélangez le jaune d’œuf et la moutarde dans un grand bol. Salez et poivrez. Incorporez l’huile de tournesol en filet en fouettant sans arrêt jusqu’à l’obtention d’une mayonnaise bien ferme.  "),
                    new Etape(4, "Dans un saladier, mélangez le céleri, les carottes et les pommes râpés.  Ajoutez l mayonnaise. Mélangez bien. Rectifiez l’assaisonnement. "),
                    new Etape(5, "Répartissez dans des petites écuelles et servez aussitôt.")

            );

            this.recettes.add(new Recette("Rémoulade de pomme verte, carotte et céleri", steps, ing, 10, 2, "", CookType.main_course, new Seasons[] {Seasons.Summer, Seasons.Fall}, 4));
        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(ingredients.get(39), 400);
            ing.put(ingredients.get(40), 1);
            ing.put(ingredients.get(36), 1);
            ing.put(ingredients.get(41), 2);
            ing.put(ingredients.get(27), 3);
            ing.put(ingredients.get(42), 1);
            ing.put(ingredients.get(43), 12);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, "Rincez et épongez le filet de saumon. Retirez les arêtes à la pince si besoin. Détaillez-le en tout petits dés."),
                    new Etape(2, "Pelez, dénoyautez et coupez la chair de la mangue en tout petits dés. Lavez, épépinez et coupez la pomme en petits morceaux."),
                    new Etape(3, "Ouvrez les fruits de la passion. Récupérez la pulpe à l’aide d’une petite cuillère. Râpez finement le zeste et pressez le jus du citron vert. Rincez, séchez et ciselez la ciboulette."),
                    new Etape(4, "Dans un saladier, mélangez le saumon avec la mangue, la pomme, la pulpe de fruits de la passion, le jus de citron vert, la ciboulette, l’huile d’olive, du sel et du poivre. "),
                    new Etape(5, "Répartissez le tartare dans des bocaux. Parsemez de zestes de citron. Réservez au frais jusqu’au moment de servir.")

            );

            this.recettes.add(new Recette("Tartare de saumon à la mangue et au fruit de la passion", steps, ing, 30, 0, "", CookType.main_course, new Seasons[] {Seasons.Summer, Seasons.Spring, Seasons.Winter}, 4));

        }
        {
            Map<Ingredient, Integer> ing = new HashMap<Ingredient, Integer>();
            ing.put(ingredients.get(44), 12);
            ing.put(ingredients.get(27), 3);
            ing.put(ingredients.get(22), 3);
            ing.put(ingredients.get(30), 2);
            ing.put(ingredients.get(20), 1);
            ing.put(ingredients.get(45), 30);

            List<Etape> steps =  Arrays.asList(
                    new Etape(1, "Coupez les pieds des artichauts. Retirez les feuilles dures de la base et taillez au ciseau le hauts des feuilles restantes."),
                    new Etape(2, "Pelez les oignons et les carottes. Taillez-les en rondelles. Emincez le poivron rouge."),
                    new Etape(3, "Faites chauffer l’huile dans une sauteuse. Faites-y revenir 2 minutes en remuant les oignons, les carottes et le poivron. Puis baissez le feu et posez par dessus, serrés les uns contre les autres et bien à plat, les artichauts. Salez et poivrez et laissez mijoter 5 minutes."),
                    new Etape(4, "Arrosez avec le vin et un verre d’eau, couvrez et laissez cuire 1 h 15. "),
                    new Etape(5, "Lavez, séchez, effeuillez et ciselez le persil. Ajoutez-le en fin de cuisson dans la sauteuse.")

            );

            this.recettes.add(new Recette("Artichauts poivrades à la barigoule", steps, ing, 20, 90, "", CookType.main_course, new Seasons[] {Seasons.Summer, Seasons.Spring, Seasons.Winter}, 6));

        }

    }

}