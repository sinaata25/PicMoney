package ataie.sina.picmoney.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ataie.sina.picmoney.List_Game;
import ataie.sina.picmoney.R;

public class Home extends Fragment {
    ImageView star_img,sandogh_gifts;
    View view;
    Button btn_list_games;
    AlphaAnimation  alphaAnimation;ScaleAnimation scaleAnimation;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home,container,false);
        this.view=view;
        Setup_views();
        Handle_Buttons();
      //  Handle_Star_Animation();
        Handle_sandogh_Animation();
        return view;
    }

    private void Handle_Buttons() {
        //button list games
        btn_list_games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), List_Game.class);
                startActivity(intent);
            }
        });



    }
    private void Setup_views() {
      //  star_img=view.findViewById(R.id.star_imgs);
        btn_list_games=view.findViewById(R.id.btn_listgames);
        sandogh_gifts=view.findViewById(R.id.sandogh_home);
    }

    private void Handle_sandogh_Animation(){
       scaleAnimation=new ScaleAnimation(1f,0.75f,1f,0.75f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setFillAfter(false);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.REVERSE);
        scaleAnimation.setInterpolator(new DecelerateInterpolator());
        sandogh_gifts.startAnimation(scaleAnimation);
    }


    @Override
    public void onStop() {
        super.onStop();
       if(scaleAnimation!=null){
            scaleAnimation.cancel();
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        sandogh_gifts.startAnimation(scaleAnimation);
    }
}
