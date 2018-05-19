package com.example.user.smartfoody.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by User on 1/1/2018.
 */

public class Produces {
    private String Id;
    private String Image;
    private String Name;
    private String Price;
    private String New;

    public Produces(){}

    public Produces(String image,String name, String price, String id)
    {
        this.Name = name;
        this.Price = price;
        this.Image = image;
        this.Id = id;
    }

    public String getImage() {
        return Image;
    }

    public String getName() {
        return Name;
    }

    public String getPrice() {
        return Price;
    }

    public String getId() {
        return Id;
    }


}
