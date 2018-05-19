package com.example.user.smartfoody.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import com.example.user.smartfoody.AcitvityHome.Scan;
import com.example.user.smartfoody.AcitvityHome.SupportMoney;
import com.example.user.smartfoody.AcitvityHome.WeatherMoney;
import com.example.user.smartfoody.R;

/**
 * Created by User on 11/27/2017.
 */

public class FoodEDay extends Fragment {

    GridLayout gridsp;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.foodeday, null);
        gridsp = (GridLayout)view.findViewById(R.id.spgrid);
        SetToggleEvent(gridsp);
        return view;
    }

    public void SetToggleEvent (GridLayout gridLayout)
    {
        for (int i=0; i< gridLayout.getChildCount(); i++)
        {
            final CardView cardView = (CardView)gridLayout.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {

                        //cardView.setCardBackgroundColor(Color.parseColor("#FFA726"));
                        Intent tospmoney = new Intent(getActivity(), WeatherMoney.class);
                        startActivity(tospmoney);
                    }
                }
            });
        }
    }
}
