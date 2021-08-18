package ataie.sina.picmoney.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ataie.sina.picmoney.R;
import ataie.sina.picmoney.Show_Ranks;

public class Jam extends Fragment {
    View view;
    Button sorat,chargozine,hads;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.jam,container,false);
        SetUpViews();
        Handle_Clicks();
        return view;
    }



    private void SetUpViews() {
        sorat=view.findViewById(R.id.btn_sorat);
        chargozine=view.findViewById(R.id.btn_char);
        hads=view.findViewById(R.id.btn_hads);
    }

    private void Handle_Clicks() {
        hads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Show_Ranks.class);
                startActivity(intent);
            }
        });

    }


}
