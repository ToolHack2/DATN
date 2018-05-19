package com.example.user.smartfoody.Login;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.smartfoody.MainActivity;
import com.example.user.smartfoody.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText firstname, lastname,regisuser,regispass, regisconfirmpass,code;
    Button register2,checked;
    LinearLayout checklayout, regislayout;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;


    private static final String registerurl = "https://nasu120696.000webhostapp.com/androidwebservice/register.php";

    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";
    PendingIntent sentIP, deliveredIP;
    BroadcastReceiver smsreceiver, smsdeli;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        //declare
        firstname = (EditText)findViewById(R.id.edtfirstname);
        lastname = (EditText)findViewById(R.id.edtlastname);
        regisuser = (EditText)findViewById(R.id.edtphone);
        regispass = (EditText)findViewById(R.id.edtregispass);
        regisconfirmpass = (EditText)findViewById(R.id.edtconfirmpass);
        code = (EditText)findViewById(R.id.edtcode);
        //button
        register2 = (Button)findViewById(R.id.btnregister2);
        checked = (Button)findViewById(R.id.btnchecked);
        //layout
        checklayout = (LinearLayout)findViewById(R.id.layoutcheck);
        regislayout = (LinearLayout)findViewById(R.id.layoutregis);

        //SMS
        sentIP = PendingIntent.getBroadcast(this,0, new Intent(SENT), 0);
        deliveredIP = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED),0);



        //
        final Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        //control
        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass = regispass.getText().toString();
                if (regisconfirmpass.getText().toString().equals(pass))
                {
                    regislayout.setVisibility(View.GONE);
                    checklayout.setVisibility(View.VISIBLE);
                    checklayout.startAnimation(slide_down);
                    sendSMS();
                }
                else 
                {
                    regisconfirmpass.setHighlightColor(Color.RED);
                    Toast.makeText(Register.this, "Mật khẩu không trùng khớp!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (code.getText().toString().equals("1234"))
                {
                    Registrasion();
                }
                else
                {
                    Toast.makeText(Register.this, "Dang ky that bai", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public void Registrasion()
    {
        StringRequest request = new StringRequest(StringRequest.Method.POST, registerurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                        try
                        {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String status = jsonObject.getString("status");

                            if (status.equals("ok"))
                            {
                                Toast.makeText(Register.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                Intent tomain = new Intent(Register.this, LogIn.class);
                                tomain.putExtra("username", regisuser.getText().toString());
                                startActivity(tomain);
                            }
                            else {
                                Toast.makeText(Register.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
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
                
                params.put("first_name", firstname.getText().toString());
                params.put("last_name", lastname.getText().toString());
                params.put("phone", regisuser.getText().toString());
                params.put("password", regispass.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }


    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(smsreceiver);
        unregisterReceiver(smsdeli);
    }

    @Override
    protected void onResume() {
        super.onResume();

        smsreceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(Register.this, "OK", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(Register.this, "Generic failure", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(Register.this, "No service", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(Register.this, "Null PDU", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(Register.this, "Radio Off", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        };

        smsdeli = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(Register.this, "OK", Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(Register.this, "SMS not delivered", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        registerReceiver(smsreceiver, new IntentFilter(SENT));
        registerReceiver(smsdeli, new IntentFilter(DELIVERED));
    }

    protected void sendSMS()
    {
        String phone = regisuser.getText().toString();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                    MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        else
        {
            SmsManager smsManager =  SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null, "1234", sentIP,deliveredIP);
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode)
//        {
//            case MY_PERMISSIONS_REQUEST_SEND_SMS:
//            {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                {
//
//                }
//                else {
//                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }
}
