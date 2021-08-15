package ataie.sina.picmoney.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ataie.sina.picmoney.R;

public class Dialog_Info extends DialogFragment {
    View view;
    TextView textView,txt;
String s;
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialog_info,container,false);
        SetUpViews();
        Handle_Try();
        return view;
    }

   private void Handle_Try() {
textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dismiss();
    }
});
    }

    private void SetUpViews() {
   textView=view.findViewById(R.id.taeed_dialog);
   txt=view.findViewById(R.id.text_dialog);
   s=getTag();
   txt.setText(s);
    }






}
