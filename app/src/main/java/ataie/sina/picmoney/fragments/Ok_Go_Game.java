package ataie.sina.picmoney.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ataie.sina.picmoney.Game;
import ataie.sina.picmoney.R;

public class Ok_Go_Game extends DialogFragment {
        View view;
        LinearLayout start;
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.ok_go_game,container,false);
        SetUpViews();
        Sets();
        return view;
    }



    private void SetUpViews() {
        start=view.findViewById(R.id.start_layout);
    }
    private void Sets() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), Game.class);
                startActivity(intent);
                dismiss();
            }
        });
    }

}
