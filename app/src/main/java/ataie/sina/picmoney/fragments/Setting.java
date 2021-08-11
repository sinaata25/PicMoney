package ataie.sina.picmoney.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ataie.sina.picmoney.R;
import ataie.sina.picmoney.models.Model_Current_User;

public class Setting extends Fragment {
    TextView phone,name;
    View view;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.setting,container,false);
        SetupViews();
        Sets();


        return view;

    }

    private void Sets() {
        name.setText(Model_Current_User.username);
        phone.setText(Model_Current_User.number);
    }

    private void SetupViews() {
        name=view.findViewById(R.id.show_name_setting);
        phone=view.findViewById(R.id.phone_show_setting);

    }

}
