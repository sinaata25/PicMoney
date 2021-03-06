package ataie.sina.picmoney;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.jackandphantom.blurimage.BlurImage;
import com.mackhartley.roundedprogressbar.RoundedProgressBar;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import ataie.sina.picmoney.models.Model_Hads;

import jp.wasabeef.picasso.transformations.BlurTransformation;


public class Game extends AppCompatActivity {
    ImageView imageView;
    TextView one,two,three,four,tasvirx;
    int javab_selected=0;
    MediaPlayer mediaPlayer;
    LinearLayout next_qus,extra_time;
    RoundedProgressBar progress;
    Timer timer;
    float progress_percent=100;
    int sampeling=8;
    ConstraintLayout loading;
    String javab_name,javab_image;
    int num=0;
    List<Model_Hads>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.game);
            SetupViews();
            Sets();
        LottieAnimationView lottieAnimationView;
        lottieAnimationView=findViewById(R.id.load_lottie);
        lottieAnimationView.playAnimation();
            Handle_Javab();
    }



    private void SetupViews() {
        imageView=findViewById(R.id.image_game);
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        tasvirx=findViewById(R.id.tasvirx);
        extra_time=findViewById(R.id.extra_time);
        next_qus=findViewById(R.id.next_question_game);
         progress=findViewById(R.id.progressBar3);
         loading=findViewById(R.id.loading_page);
        list=new ArrayList<>();
    }

    private void Sets() {
        get_num();
    }

    private void get_num() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.url_get_num_hads, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                num=Integer.parseInt(response);
                if(num>0){
                    int min = 1;
                    int max = num;
                    int random = new Random().nextInt((max - min) + 1) + min;
                    random= check_javab_dade(random);
                    get_data_from_server(random);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param;
                param = new HashMap<>();
                param.put("key", Statics.key_get_num_hads);
                //  param.put("num", Statics.key_get_hads);
                return param;
            }
        };


        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy());
        requestQueue.add(stringRequest);


    }

    private void get_data_from_server(int randnum) {
        int min = 1;
        int max = 4;
        int rand_javab = new Random().nextInt((max - min) + 1) + min;
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.url_get_hads, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        Model_Hads model_hads=new Model_Hads();
                        model_hads.setName1(jsonObject.getString("name1"));
                        model_hads.setName2(jsonObject.getString("name2"));
                        model_hads.setName3(jsonObject.getString("name3"));
                        model_hads.setName4(jsonObject.getString("name4"));
                        model_hads.setImage1(jsonObject.getString("image1"));
                        model_hads.setImage2(jsonObject.getString("image2"));
                        model_hads.setImage3(jsonObject.getString("image3"));
                        model_hads.setImage4(jsonObject.getString("image4"));
                        list.add(model_hads);
                    }
                    Set_Txts(rand_javab);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param;
                param = new HashMap<>();
                param.put("key", Statics.key_get_hads);
                param.put("num", String.valueOf(randnum));
                return param;
            }
        };


        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy());
        requestQueue.add(stringRequest);



    }

    void  Set_Txts(int rand){
        int x=list.size()-1;
        switch (rand){
            case 1:javab_name=list.get(x).getName1();javab_image=list.get(x).getImage1();break;
            case 2:javab_name=list.get(x).getName2();javab_image=list.get(x).getImage2();break;
            case 3:javab_name=list.get(x).getName3();javab_image=list.get(x).getImage3();break;
            case 4:javab_name=list.get(x).getName4();javab_image=list.get(x).getImage4();break;
        }
        one.setText(list.get(x).getName1());
        two.setText(list.get(x).getName2());
        three.setText(list.get(x).getName3());
        four.setText(list.get(x).getName4());
        tasvirx.setText("?????????? "+list.size());
        progress.setProgressPercentage(progress_percent,false);
        Load_image();


    }

    void Load_image(){
        Picasso.get()
                .load(javab_image)
                .transform(new BlurTransformation(getApplicationContext(), 25, sampeling))
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                           loading.setVisibility(View.GONE);
                           Start_Game();
                    }

                    @Override
                    public void onError(Exception e) {
                            Load_image();
                    }
                });
    }

        void Start_Game (){
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.tic);
        mediaPlayer.start();
     timer=new Timer();
    timer.schedule(new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(progress_percent==80){
                        Handle_Speed((float) 1.2);
                        progress.setProgressDrawableColor(Color.rgb(0,255,0));
                    }else if (progress_percent==50){
                    Handle_Speed((float) 1.5);
                        progress.setProgressDrawableColor(Color.rgb(255,255,0));
                    }else if (progress_percent==30){
                        Handle_Speed((float) 2.3);
                        progress.setProgressDrawableColor(Color.rgb(128,0,0));
                    }
                    else if (progress_percent==10){
                        Handle_Speed((float) 2.8);
                        progress.setProgressDrawableColor(Color.rgb(0,0,0));
                    }else if (progress_percent==0){
                        Go_To_Show(-1);
                        if(mediaPlayer!=null){
                            mediaPlayer.release();
                        }

                    }
                    progress.setProgressPercentage(progress_percent,false);
                    progress_percent-=.5;
                }
            });
        }
    },50,50);
}


        void Handle_Speed(float s){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(s));
            }
        }catch (Exception e){
            Log.i("TAG", "Handle_Speed: "+e.getMessage());
        }

        }




    private void Handle_Javab() {
        //////////////////////////////1
     one.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(javab_selected==0){
            javab_selected=1;
           String this_gozine= one.getText().toString();
           if(this_gozine.equals(javab_name)){
                    Go_To_Show(1);
           }else {
               Go_To_Show(0);
           }
        }
    }
    });
     //////////////////////////////////2
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(javab_selected==0){
                    javab_selected=2;
                    String this_gozine= two.getText().toString();
                    if(this_gozine.equals(javab_name)){
                        Go_To_Show(1);
                    }else {
                        Go_To_Show(0);
                    }
                }
            }
        });
        ///////////////////////////////////3
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(javab_selected==0){
                    javab_selected=3;
                    String this_gozine= three.getText().toString();
                    if(this_gozine.equals(javab_name)){
                        Go_To_Show(1);
                    }else {
                        Go_To_Show(0);
                    }
                }
            }
        });
        ///////////////////////////////////4
       four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(javab_selected==0){
                    javab_selected=4;
                    String this_gozine=four.getText().toString();
                    if(this_gozine.equals(javab_name)){
                        Go_To_Show(1);
                    }else {
                        Go_To_Show(0);
                    }
                }
            }
        });
        ///////////////////////////////////
        next_qus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.release();
                timer.purge();
                timer.cancel();
                finish();
                Intent intent=new Intent(getApplicationContext(),Game.class);
                startActivity(intent);
            }
        });


    }






        void Go_To_Show(int vaziat){
            if(mediaPlayer!=null){
                mediaPlayer.release();
            }
            timer.cancel();
            timer.purge();
        if(vaziat==1){
            Intent intent=new Intent(getApplicationContext(),Win_Lose.class);
            intent.putExtra("win",1);
            intent.putExtra("javab_name",javab_name);
            intent.putExtra("javab_image",javab_image);
            startActivity(intent);
            finish();
        }else if(vaziat==-1){
            Intent intent=new Intent(getApplicationContext(),Win_Lose.class);
            intent.putExtra("win",-1);
            intent.putExtra("javab_name",javab_name);
            intent.putExtra("javab_image",javab_image);
            startActivity(intent);
            finish();
        } else {
            Intent intent=new Intent(getApplicationContext(),Win_Lose.class);
            intent.putExtra("win",0);
            intent.putExtra("javab_name",javab_name);
            intent.putExtra("javab_image",javab_image);
            startActivity(intent);
            finish();
        }

        }



 int check_javab_dade(int rand){

        if(Statics.list_game!=null){
            for(int i=0;i<Statics.list_game.size();i++){
                if(rand==Statics.list_game.get(i)){
                    if(rand+1<=num){
                        check_javab_dade(rand+1);
                    }
                }
            }

        }

            Statics.list_game.add(rand);
        return rand;
 }








}