package com.example.user.smartfoody.AcitvityHome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.smartfoody.Adapter.SP_MoneyAdapter;
import com.example.user.smartfoody.Model.Produces;
import com.example.user.smartfoody.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 20/03/2018.
 */

public class MoneyMenu extends AppCompatActivity {

    SP_MoneyAdapter adapter;
    ArrayList<Produces> splist;
    ListView viewsp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.money_menu);

        //
        viewsp = (ListView)findViewById(R.id.listmenu);
        splist = new ArrayList<>();

        ReadJSON("https://nasu120696.000webhostapp.com/androidwebservice/getxao.php", splist, viewsp);


    }

    public void ReadJSON (final String url, final ArrayList<Produces> mlist, final ListView mview)
    {


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            for (int i=0; i<response.length(); i++)
                            {

                                JSONObject object = response.getJSONObject(i);
                                String imgurl = object.getString("Hinh");
                                String name = object.getString("Ten");
                                String money = object.getString("Gia");
                                String id = object.getString("ID");
                                mlist.add(new Produces(imgurl, name, money, id));

                            }
                            adapter = new SP_MoneyAdapter(mlist,MoneyMenu.this);
                            mview.setAdapter( adapter);

                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(MoneyMenu.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        int socketTimeout = 3000;//3 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonArrayRequest.setRetryPolicy(policy);
        requestQueue.add(jsonArrayRequest);

    }
}
