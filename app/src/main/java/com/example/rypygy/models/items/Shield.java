package com.example.rypygy.models.items;

import androidx.annotation.NonNull;

import com.example.rypygy.models.Item;

public class Shield extends Item {
    public enum Type {WOODEN, STONE, IRON}
    public Shield(@NonNull Type t) {
        super(Category.SHIELD);
        switch (t) {
            case WOODEN:
                this.setName("Wooden Shield");
                this.setPrice(15);
                setDefense(2);
                break;
            case STONE:
                this.setName("Stone Shield");
                this.setPrice(30);
                setDefense(4);
                break;
            case IRON:
                this.setName("Iron Shield");
                this.setPrice(15);
                setDefense(6);
                break;
        }
    }
}
