package com.example.recyclerview;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MonAdapter monAdapter;
    private RecyclerView recyclerView;
    private List<Items> myItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.myRecyclerView);
        myItems = new ArrayList<>();

        myItems.add(new Items(R.drawable.cupheadicon, "Cuphead", "Try to not break your device"));
        myItems.add(new Items(R.drawable.marioicon, "Super Mario Bros", "Try to not break your device"));
        myItems.add(new Items(R.drawable.minecrafticon, "Minecraft", "Try to not break your device"));
        myItems.add(new Items(R.drawable.dsticon, "Don't Starve", "Try to not break your device"));
        myItems.add(new Items(R.drawable.pokemonicon, "Pokemon", "Try to not break your device"));
        myItems.add(new Items(R.drawable.subnauticaicon, "Subnautica", "Try to not break your device"));
        myItems.add(new Items(R.drawable.cyberpunkicon, "Cyber Punk 2077", "Try to not break your device"));
        myItems.add(new Items(R.drawable.counterstrikeicon, "Counter strike", "Try to not break your device"));
        myItems.add(new Items(R.drawable.eldenringicon, "Elden Ring", "Try to not break your device"));
        myItems.add(new Items(R.drawable.narutoicon, "Naruto", "Try to not break your device"));
        myItems.add(new Items(R.drawable.genshinicon, "Genshin Impact", "Try to not break your device"));

        monAdapter = new MonAdapter(myItems); //j'instancie mon adapter en lui donnant ma liste

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        int orientation = getResources().getConfiguration().orientation; // objet orientation détécte l'orientation
        if(Configuration.ORIENTATION_LANDSCAPE == orientation) //si paysage choisit la grille sinon en ligne
        {
            recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }

        recyclerView.setAdapter(monAdapter); //J'affiche mon adapter dans mon recycleView

        EffetSwipe effetSwipe = new EffetSwipe(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, MainActivity.this, myItems, monAdapter);
        new ItemTouchHelper(effetSwipe).attachToRecyclerView(recyclerView);
    }
}