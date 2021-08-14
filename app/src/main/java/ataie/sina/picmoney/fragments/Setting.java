package ataie.sina.picmoney.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ataie.sina.picmoney.R;
import ataie.sina.picmoney.models.Model_Current_User;

public class Setting extends Fragment {
    TextView phone,name;
    LinearLayout exit,change_pass;
    View view;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.setting,container,false);
        SetupViews();
        Sets();
        Handle_Clicks();
        return view;

    }

    private void Handle_Clicks() {

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wanna_Exit wanna_exit=new Wanna_Exit();
                wanna_exit.show(getParentFragmentManager(),"");
            }
        });
        ////////////////////
        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Change_Pass change_pass=new Change_Pass();
                change_pass.show(getParentFragmentManager(),"");
            }
        });
    }

    private void Sets() {
        name.setText(Model_Current_User.username);
        phone.setText(Model_Current_User.number);
    }

    private void SetupViews() {
        name=view.findViewById(R.id.show_name_setting);
        phone=view.findViewById(R.id.phone_show_setting);
        exit=view.findViewById(R.id.exit_setting);
        change_pass=view.findViewById(R.id.change_pass_setting);

    }

}
