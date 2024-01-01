package com.example.rypygy.save;

import com.example.rypygy.models.Inventory;
import com.example.rypygy.models.Item;

import java.util.ArrayList;
import java.util.List;

public class SaveInventory {
    private List<SaveItem> inventory = new ArrayList<>();

    public SaveInventory() {
        for (Item item : Inventory.getInventory()) {
            if (item.getAttributes().containsKey(Item.Attribute.EQUIPPED)) {
                if (item.getAttributes().get(Item.Attribute.EQUIPPED) == 1.0) {
                    inventory.add(new SaveItem(item.getId(), item.getAmount(), true));
                    continue;
                }
            }
            inventory.add(new SaveItem(item.getId(), item.getAmount()));
        }
    }

    public List<SaveItem> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return "SaveInventory{" +
                "inventory=" + inventory +
                '}';
    }
}
