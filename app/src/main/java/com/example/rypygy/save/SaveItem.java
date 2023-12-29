package com.example.rypygy.save;

import com.example.rypygy.models.Item;

public class SaveItem {
    private Item.PredefinedItems id;
    private int amount;
    private boolean isEquipped;

    public SaveItem(Item.PredefinedItems id, int amount, boolean isEquipped) {
        this.id = id;
        this.amount = amount;
        this.isEquipped = isEquipped;
    }

    public SaveItem(Item.PredefinedItems id, int amount) {
        this.id = id;
        this.amount = amount;
        isEquipped = false;
    }

    public Item.PredefinedItems getId() {
        return id;
    }
    public int getAmount() {
        return amount;
    }
    public boolean isEquipped() {
        return isEquipped;
    }

    @Override
    public String toString() {
        return "SaveItem{" +
                "id=" + id +
                ", amount=" + amount +
                ", isEquipped=" + isEquipped +
                '}';
    }
}
