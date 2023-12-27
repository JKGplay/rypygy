package com.example.rypygy.models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private static List<Item> inventory = new ArrayList<Item>();

    public static void addItem(Item item) {
        if (inventory.contains(item)) {
            inventory.get(inventory.indexOf(item)).addAmount(item.getAmount());
        } else {
            inventory.add(item);
        }
    }

    public static void removeItem(Item item) {
        if (inventory.contains(item)) {
            int index = inventory.indexOf(item);
            inventory.get(index).removeAmount(item.getAmount());
            if (inventory.get(index).getAmount() <= 0) {
                inventory.remove(index);
            }
        }
    }

    public static boolean isEquipped(Item item) {
        if (item.getCategory().equals(Item.Category.WEAPON) || item.getCategory().equals(Item.Category.ARMOR)) {
            return item.getAttributes().get(Item.Attribute.EQUIPPED) == 1.0;
        }
        return false;
    }

    public static boolean hasEquipped(Item.Category category) {
        for (Item item : inventory) {
            if (item.getCategory().equals(category)) {
                return item.getAttributes().get(Item.Attribute.EQUIPPED) == 1.0;
            }
        }
        return false;
    }

    public static Item getEquipped(Item.Category category) {
        Item toReturn = null;
        for (Item item : inventory) {
            if (item.getCategory().equals(category)) {
                if (item.getAttributes().get(Item.Attribute.EQUIPPED) == 1.0) {
                    toReturn = item;
                }
            }
        }
        return toReturn;
    }

    public static void equipItem(Item item) {
        if (inventory.contains(item)) {
            if (item.getCategory().equals(Item.Category.WEAPON)) {
                unEquipItem(Item.Category.WEAPON);
            } else if (item.getCategory().equals(Item.Category.ARMOR)) {
                unEquipItem(Item.Category.ARMOR);
            }
            inventory.get(inventory.indexOf(item)).getAttributes().replace(Item.Attribute.EQUIPPED, 1.0);
        }
    }

    public static void unEquipItem(Item.Category category) {
        if (category.equals(Item.Category.WEAPON) || category.equals(Item.Category.ARMOR)) {
            for (Item item : inventory) {
                if (item.getCategory().equals(category)) {
                    if (item.getAttributes().get(Item.Attribute.EQUIPPED) == 1.0) {
                        inventory.get(inventory.indexOf(item)).getAttributes().replace(Item.Attribute.EQUIPPED, 0.0);
                    }
                }
            }
        }
    }

    public static List<Item> listOfUsableItems() {
        List<Item> toReturn = new ArrayList<>();
        for (Item item : inventory) {
            if (item.getCategory().equals(Item.Category.POTION) || item.getCategory().equals(Item.Category.SCROLL)) {
                toReturn.add(item);
            }
        }
        return toReturn;
    }

    public static List<String> listOfNamesOfUsableItems() {
        List<String> toReturn = new ArrayList<>();
        for (Item item : listOfUsableItems()) {
            toReturn.add(item.getName());
        }
        return toReturn;
    }

    public static List<Item> getInventory() {
        return inventory;
    }
}
