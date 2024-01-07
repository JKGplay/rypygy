package com.example.rypygy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rypygy.data.NpcData;
import com.example.rypygy.enums.Location;
import com.example.rypygy.models.Character;
import com.example.rypygy.models.Inventory;
import com.example.rypygy.models.Item;
import com.example.rypygy.save.Save;
import com.example.rypygy.save.SaveCharacter;
import com.example.rypygy.save.SaveEncounters;
import com.example.rypygy.save.SaveInventory;
import com.example.rypygy.save.SaveItem;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etName;
    private Button btnPlay, btnLoad;
    private ImageView ivLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        btnPlay = findViewById(R.id.btnPlay);
        btnLoad = findViewById(R.id.btnLoad);
        ivLoad = findViewById(R.id.ivLoad);

        File file = new File(getFilesDir().getPath() + "/data.json");

        if (!file.exists()) {
            btnLoad.setVisibility(View.GONE);
            ivLoad.setVisibility(View.GONE);
        }

        btnPlay.setOnClickListener(view -> {
            if (!String.valueOf(etName.getText()).trim().isEmpty()) {
                Character.setName(String.valueOf(etName.getText()).trim());
                Item startingWeapon = new Item(Item.PredefinedItems.SHORT_SWORD, 1, true);
                Item startingArmor = new Item(Item.PredefinedItems.RAGS, 1, true);
                Log.d("inv", Inventory.getInventory().toString());
                Inventory.addItem(startingWeapon);
                Inventory.addItem(startingArmor);
                if (file.exists()) {
                    file.delete();
                }
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Enter a name for your character", Toast.LENGTH_SHORT).show();
            }
        });

        btnLoad.setOnClickListener(view -> {
            Gson gson = new Gson();
            try {
                FileReader reader = new FileReader(getFilesDir().getPath() + "/data.json");
                Save load = gson.fromJson(reader, Save.class);
                Log.d("load", load.toString());
                assignData(load);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void assignData(Save load) {
        if (load == null) {
            return;
        }
        SaveCharacter character = load.getCharacter();
        SaveInventory inventory = load.getInventory();
        SaveEncounters encounters = load.getEncounters();

        Character.setName(character.getName());
        Character.setLevel(character.getLevel());
        Character.setXp(character.getXp());
        Character.setStrength(character.getStrength());
        Character.setDexterity(character.getDexterity());
        Character.setVitality(character.getVitality());
        Character.setCurHP(character.getCurHP());
        Character.setGold(character.getGold());

        for (SaveItem current : inventory.getInventory()) {
            Inventory.addItem(new Item(current.getId(), current.getAmount(), current.isEquipped()));
        }

        NpcData.isVisited.replace(Location.FOREST, encounters.isForest());
        NpcData.isVisited.replace(Location.GARAGES, encounters.isGarages());
        NpcData.isVisited.replace(Location.TOILETS, encounters.isToilets());
        NpcData.isVisited.replace(Location.COMPUTER_LAB, encounters.isComputerLab());
        NpcData.isVisited.replace(Location.DORMITORY, encounters.isDormitory());
        NpcData.isVisited.replace(Location.COURTYARD, encounters.isCourtyard());
        NpcData.isVisited.replace(Location.KACZYCE, encounters.isKaczyce());
    }
}