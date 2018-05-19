package com.example.user.smartfoody.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.smartfoody.MainActivity;
import com.example.user.smartfoody.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity {

    EditText username, password;
    Button login, register,forgotpass;
    private static final String loginurl = "https://nasu120696.000webhostapp.com/androidwebservice/login.php";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login);


        //control
        username = (EditText)findViewById(R.id.edtuser);
        password = (EditText)findViewById(R.id.edtpass);

        login = (Button)findViewById(R.id.btnlogin);
        register = (Button)findViewById(R.id.btndangky);
        forgotpass = (Button)findViewById(R.id.btnforgot);

        //event
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toregis = new Intent(LogIn.this, Register.class);
                startActivity(toregis);
            }
        });

        //login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });

    }

    public void Login()
    {
        StringRequest request = new StringRequest(StringRequest.Method.POST, loginurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(LogIn.this, response, Toast.LENGTH_SHORT).show();
                        try
                        {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String status = jsonObject.getString("status");
                            username.setText(status);

                            if (status.equals("ok"))
                            {
                                Intent tomain = new Intent(LogIn.this, MainActivity.class);
                                tomain.putExtra("username", username.getText().toString());
                                startActivity(tomain);
                            }
                            else {
                                Toast.makeText(LogIn.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
         )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("loginuser", "true");
                params.put("phone", username.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}
