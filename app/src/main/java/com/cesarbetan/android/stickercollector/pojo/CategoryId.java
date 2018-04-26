package com.cesarbetan.android.stickercollector.pojo;

/**
 * Created by Cesar on 23/04/18.
 */

public class CategoryId {

    public String categoryId;

    public <T extends CategoryId> T withId(final String id){
        this.categoryId = id;
        return (T) this;
    }

}
