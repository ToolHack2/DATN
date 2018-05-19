package com.example.user.smartfoody.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.user.smartfoody.AcitvityHome.Scan;
import com.example.user.smartfoody.R;
import com.example.user.smartfoody.View.ViewVideo;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

/**
 * Created by User on 11/27/2017.
 */

public class Home extends Fragment {
    String arrayName[] = {"QR code", "Báo cáo", "Cá nhân"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        CircleMenu circleMenu = (CircleMenu)view.findViewById(R.id.circle);
        circleMenu.setMainMenu(Color.parseColor("#424242"),R.drawable.ic_add, R.drawable.ic_clear).
                addSubMenu(Color.parseColor("#FF9800"), R.drawable.icon_scan)
                .addSubMenu(Color.parseColor("#795548"),R.drawable.icon_report)
                .addSubMenu(Color.parseColor("#64DD17"),R.drawable.icon_user)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        switch (i) {
                            case 0:
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent toscan = new Intent(getActivity(), Scan.class);
                                        startActivity(toscan);
                                    }
                                }, 600);

                                break;
                            case 1:
                                Toast.makeText(getActivity(), "Reported", Toast.LENGTH_SHORT).show();
                                //Intent toreport = new Intent(getContext(), Scan.class);
                                //startActivity(toreport);
                        }
                    }
                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {
            @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {

            }
        });
        return view;
    }

}
