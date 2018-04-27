package com.cesarbetan.android.stickercollector.pojo;

/**
 * Created by Cesar on 26/04/18.
 */

public class Sticker extends CategoryId{
    String number, owned, quantity;

    public Sticker() {
    }

    public Sticker(String number, String owned, String quantity) {
        this.number = number;
        this.owned = owned;
        this.quantity = quantity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwned() {
        return owned;
    }

    public void setOwned(String owned) {
        this.owned = owned;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
