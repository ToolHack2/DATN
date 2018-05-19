package com.example.user.smartfoody.AcitvityHome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.smartfoody.Adapter.CartAdapter;
import com.example.user.smartfoody.Adapter.ProducesAdapter;
import com.example.user.smartfoody.CartDB.Database;
import com.example.user.smartfoody.Fragment.Shop;
import com.example.user.smartfoody.MainActivity;
import com.example.user.smartfoody.Model.Oder;
import com.example.user.smartfoody.Model.Produces;
import com.example.user.smartfoody.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {

    public CartAdapter adapter;
    public RecyclerView list_cart;
    public List<Oder> templist;
    public TextView sum_money;
    Button cartback;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);


        list_cart = (RecyclerView)findViewById(R.id.list_cart);
        list_cart.setHasFixedSize(false);
        list_cart.setLayoutManager(new GridLayoutManager(this, 1));
        sum_money = (TextView)findViewById(R.id.txtsummon);
        cartback = (Button)findViewById(R.id.btncartback);

        // get list produces from intent
        LoadListCart();



        // cart back - failed
        cartback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("relist", (Serializable) templist);
                Shop shop = new Shop();
                shop.setArguments(bundle1);
                finish();
            }
        });
    }

    public void LoadListCart()
    {
        templist = new Database(this).getCart();
        adapter = new CartAdapter(this,templist);
        list_cart.setAdapter(adapter);
        int total = 0;
        for (Oder item:templist) {
            total += Integer.parseInt(item.getProducePrice()) * Integer.parseInt(item.getProduceQuantity());
        }
        Locale locale = new Locale("en", "US");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        sum_money.setText(format.format(total));
    }

}
