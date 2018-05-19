package com.example.user.smartfoody;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.smartfoody.Adapter.ProducesAdapter;
import com.example.user.smartfoody.Fragment.FoodEDay;
import com.example.user.smartfoody.Fragment.Home;
import com.example.user.smartfoody.Fragment.Shop;
import com.example.user.smartfoody.Model.Produces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  {
    private RequestQueue requestQueue;

    private BottomNavigationView bottomview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Home home = new Home();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, home).commit();
        requestQueue = Volley.newRequestQueue(this);
        //ReadJSON("https://nasu120696.000webhostapp.com/androidwebservice/getcanh.php");

        bottomview = (BottomNavigationView)findViewById(R.id.menubottom);
        bottomview.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.home)
                {
                    Home home = new Home();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, home).commit();
                    return  true;
                }
                if (item.getItemId() == R.id.shop)
                {
                    Shop shop = new Shop();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, shop).commit();
                    return  true;
                }
                if (item.getItemId() == R.id.food)
                {
                    FoodEDay foodEDay = new FoodEDay();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, foodEDay).commit();
                    return  true;
                }
                return true;
            }
        });



    }

    private void ReadJSON (String url)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        int socketTimeout = 3000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonArrayRequest.setRetryPolicy(policy);
        requestQueue.add(jsonArrayRequest);

    }


}
