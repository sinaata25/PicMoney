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

public class Credit extends Fragment {
    TextView money,coin;
    View view;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.credit,container,false);
         SetUpViews();
         Sets();
        return view;
    }

    private void Sets() {
        money.setText(Model_Current_User.money+"تومان");
        coin.setText(Model_Current_User.coin);
    }

    private void SetUpViews() {
     money=view.findViewById(R.id.show_money_credit);
        coin=view.findViewById(R.id.show_coin_credit);

    }

}
