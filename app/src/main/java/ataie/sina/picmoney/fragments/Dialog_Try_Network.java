package ataie.sina.picmoney.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;

import org.jetbrains.annotations.NotNull;

import ataie.sina.picmoney.R;

public class Dialog_Try_Network extends DialogFragment {
    View view;
    TextView textView;
    LottieAnimationView lottieAnimationView;
     public static   Callback_Try callback_try;
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialog_try_network,container,false);
        SetUpViews();
        Handle_Try();
        return view;
    }

   private void Handle_Try() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callback_try!=null){
                    callback_try.onTry();
                }
            }
        });
    }

    private void SetUpViews() {
     textView=view.findViewById(R.id.try_again);
     lottieAnimationView=view.findViewById(R.id.eror_lottie);
     lottieAnimationView.playAnimation();
    }



    public interface Callback_Try{
        void onTry();
    }



}
