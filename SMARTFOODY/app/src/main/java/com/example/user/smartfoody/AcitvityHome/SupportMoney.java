package com.example.user.smartfoody.AcitvityHome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.user.smartfoody.R;

/**
 * Created by User on 14/03/2018.
 */

public class SupportMoney extends AppCompatActivity {

    CardView card1, card2, card3;
    Button btntoview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_money);

        btntoview = (Button)findViewById(R.id.btnview);
        btntoview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tospview = new Intent(SupportMoney.this, MoneyMenu.class);
                startActivity(tospview);
            }
        });


    }


}
