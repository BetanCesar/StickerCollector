package com.cesarbetan.android.stickercollector.pojo;

/**
 * Created by Cesar on 23/04/18.
 */

public class Category extends CategoryId{
    String name, owned, total;

    public Category() {
    }

    public Category(String name, String owned, String total) {
        this.name = name;
        this.owned = owned;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwned() {
        return owned;
    }

    public void setOwned(String owned) {
        this.owned = owned;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
