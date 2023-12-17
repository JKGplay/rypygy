package com.example.rypygy.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.rypygy.R;
import com.example.rypygy.enums.Location;
import com.example.rypygy.models.Character;
import com.example.rypygy.models.Item;
import com.example.rypygy.models.Rnd;

import org.apache.commons.text.WordUtils;

import java.util.HashMap;

public class ItemData {
    private String title, message;
    private Item drawnItem;
    private Location location;

    private static final HashMap<Location, HashMap<Item, Integer>> CHANCES = new HashMap<Location, HashMap<Item, Integer>>() {{
        put(Location.FOREST, new HashMap<Item, Integer>() {{
            put(new Item(Item.PredefinedItems.LONG_SWORD, 1), 15);
            put(new Item(Item.PredefinedItems.CLAYMORE, 1), 10);
            put(new Item(Item.PredefinedItems.LEATHER_ARMOR, 1), 10);
            put(new Item(Item.PredefinedItems.WAR_HAMMER, 1), 15);
            put(new Item(Item.PredefinedItems.SMALL_POTION, 1), 20);
            put(new Item(Item.PredefinedItems.MEDIUM_POTION, 1), 15);
            put(new Item(Item.PredefinedItems.SCROLL_OF_RETURN, 1), 15);
        }});
        put(Location.GARAGES, new HashMap<Item, Integer>() {{
            put(new Item(Item.PredefinedItems.MEDIUM_POTION, 1), 100);
        }});
        put(Location.TOILETS, new HashMap<Item, Integer>() {{
            put(new Item(Item.PredefinedItems.MEDIUM_POTION, 1), 100);
        }});
        put(Location.COMPUTER_LAB, new HashMap<Item, Integer>() {{
            put(new Item(Item.PredefinedItems.MEDIUM_POTION, 1), 100);
        }});
        put(Location.DORMITORY, new HashMap<Item, Integer>() {{
            put(new Item(Item.PredefinedItems.MEDIUM_POTION, 1), 100);
        }});
        put(Location.COURTYARD, new HashMap<Item, Integer>() {{
            put(new Item(Item.PredefinedItems.MEDIUM_POTION, 1), 100);
        }});
        put(Location.KACZYCE, new HashMap<Item, Integer>() {{
            put(new Item(Item.PredefinedItems.MEDIUM_POTION, 1), 100);
        }});

    }};

    public ItemData(@NonNull Location location, @NonNull Context context) {
        this.location = location;
        drawnItem = draw();
        title = context.getResources().getString(R.string.location_title, WordUtils.capitalizeFully(location.toString().replace('_', ' ')));
        message = context.getResources().getString(R.string.item_message, drawnItem.getName());
    }

    private Item draw() {
        int[] chances = CHANCES.get(location).values().stream().mapToInt(Integer::intValue).toArray();
        int rnd = Rnd.rnd(1, 100);
        int i = 0;
        int value = chances[i];
        Item toReturn = null;
        for (Item t : CHANCES.get(location).keySet()) {
            if (value >= rnd) {
                toReturn = t;
                break;
            }
            i++;
            value += chances[i];
        }
        return toReturn;
    }

    public void action() {
        Character.addItem(drawnItem, 1);
    }

    public String getTitle() {
        return title;
    }
    public String getMessage() {
        return message;
    }
}
