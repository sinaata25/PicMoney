package ataie.sina.picmoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ataie.sina.picmoney.fragments.Ok_Go_Game;
import ataie.sina.picmoney.models.Model_Current_User;

public class List_Game extends AppCompatActivity {
        CardView sangi,boronzi,silver,gold,diamond;
        TextView textView_coin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_game);
        SetUpViews();
        Sets();
        HandleCardClicks();
    }


    private void SetUpViews() {
    //    sangi=findViewById(R.id.cardsangi);
      //  boronzi=findViewById(R.id.cardboronzi);
        silver=findViewById(R.id.cardsilver);
        gold=findViewById(R.id.cardgold);
        diamond=findViewById(R.id.caarddiamond);
        textView_coin=findViewById(R.id.show_coin_list);

    }


    private void Sets() {
    textView_coin.setText(Model_Current_User.coin);
    }




    private void HandleCardClicks() {
/*        //sangi
        sangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Ok_Go_Game ok_go_game=new Ok_Go_Game();
               // ok_go_game.show(getSupportFragmentManager(),"");
                Intent intent=new Intent(getApplicationContext(),Game.class);
                startActivity(intent);
            }
        });
        //boronzi
        boronzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ok_Go_Game ok_go_game=new Ok_Go_Game();
                ok_go_game.show(getSupportFragmentManager(),"");
            }
        });*/
        //silver
        silver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ok_Go_Game ok_go_game=new Ok_Go_Game();
                ok_go_game.show(getSupportFragmentManager(),"");
            }
        });
        //gold
        gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ok_Go_Game ok_go_game=new Ok_Go_Game();
                ok_go_game.show(getSupportFragmentManager(),"");
            }
        });
        //diamond
        diamond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ok_Go_Game ok_go_game=new Ok_Go_Game();
                ok_go_game.show(getSupportFragmentManager(),"");
            }
        });






    }









}