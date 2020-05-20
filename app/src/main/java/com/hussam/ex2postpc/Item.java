package com.hussam.ex2postpc;

import android.widget.TextView;

public class Item {
    private String itemText;
    private boolean clicked;

    Item(String text, boolean clicks){
        itemText = text;
        clicked = clicks;
    }
    void setClicked(){
        clicked = true;
    }
    String getItemText(){
        return itemText;
    }
    boolean isClicked(){
        return clicked;
    }

}
