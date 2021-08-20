package ataie.sina.picmoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ataie.sina.picmoney.models.Model_Current_User;
import ataie.sina.picmoney.models.Model_Hads;

public class Win_Lose extends AppCompatActivity {
    int win;
    MediaPlayer mediaPlayer;
    TextView javab_dadi,show_coin_win,show_coin_lose,javab_lose,javab_win,gozaresh_win,gozaresh_lose,bazgasht_lose,next_soal_win;
    ImageView image_lose,image_win;
    CardView cardwin,lcardose;
    String javab_image,javab_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.win_lose);
         SetUpViews();
         getIntents();
        Sets();

    }



    private void SetUpViews() {
        cardwin=findViewById(R.id.card_win);
        lcardose=findViewById(R.id.card_lose);
        javab_dadi=findViewById(R.id.javab_dadi);
        show_coin_win=findViewById(R.id.show_coin_win);
        show_coin_lose=findViewById(R.id.show_coin_lose);
        javab_lose=findViewById(R.id.javab_lose);
        javab_win=findViewById(R.id.javab_win);
        gozaresh_win=findViewById(R.id.gozaresh_win);
        gozaresh_lose=findViewById(R.id.gozaresh_lose);
        bazgasht_lose=findViewById(R.id.bazgasht_loze);
        next_soal_win=findViewById(R.id.next_question_win);
        image_lose=findViewById(R.id.image_lose);
        image_win=findViewById(R.id.image_win);

    }


    void getIntents(){
        Intent intent = getIntent();
         win=intent.getIntExtra("win",0);
         javab_image=intent.getStringExtra("javab_image");
         javab_name=intent.getStringExtra("javab_name");
         if(win==1){
             cardwin.setVisibility(View.VISIBLE);
         }else if(win==-1){
             lcardose.setVisibility(View.VISIBLE);
             javab_dadi.setText("زمانت تموم شد!");
         } else{
             lcardose.setVisibility(View.VISIBLE);
         }
    }


    private void Sets() {
        if(win==1){
            Handle_Win();
        }else if(win==0 || win==-1){
            Handle_Lose();
        }

    }


    void Handle_Win(){
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.winsound);
        mediaPlayer.start();
        show_coin_win.setText(Model_Current_User.coin);
        if(javab_name!=null || javab_image!=null){
            Picasso.get().load(javab_image) .error(R.drawable.nowifi).into(image_win);
        }
        javab_win.setText(javab_name);
        ////////////////
        gozaresh_win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ////////////////
        next_soal_win.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Game.class);
                startActivity(intent);
                finish();
            }
        });
        //////////////


    }

    void Handle_Lose(){
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.losesound);
        mediaPlayer.start();
    show_coin_lose.setText(Model_Current_User.coin);
        if(javab_name!=null || javab_image!=null){
            Picasso.get().load(javab_image) .error(R.drawable.nowifi).into(image_lose);
        }
        javab_lose.setText(javab_name);
        ///////////////
        gozaresh_lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //////////////
        bazgasht_lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ////////////

    }



}