package ataie.sina.picmoney.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ataie.sina.picmoney.Game;
import ataie.sina.picmoney.Login_Logup;
import ataie.sina.picmoney.R;

public class Wanna_Exit extends DialogFragment {
        View view;
        TextView yes,no;
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.wanna_exit,container,false);
        SetUpViews();
        Sets();
        return view;
    }



    private void SetUpViews(){
yes=view.findViewById(R.id.yes);
no=view.findViewById(R.id.no);
    }
    private void Sets() {
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getContext().getSharedPreferences("shared", Context.MODE_PRIVATE).edit();
                editor.putInt("is_login",0);
                editor.putString("username","");
                editor.putString("number","");
                editor.apply();
                dismiss();
                Intent intent=new Intent(getContext(), Login_Logup.class);
                startActivity(intent);
            }
        });
        ////
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }



}
