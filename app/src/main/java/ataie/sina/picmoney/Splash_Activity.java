package ataie.sina.picmoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jawnnypoo.physicslayout.PhysicsLinearLayout;
import com.mackhartley.roundedprogressbar.RoundedProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ataie.sina.picmoney.fragments.Dialog_Try_Network;
import ataie.sina.picmoney.fragments.Ok_Go_Game;
import ataie.sina.picmoney.models.Model_Current_User;

public class Splash_Activity extends AppCompatActivity {
Map<String,String> param;
RoundedProgressBar progressBar;
TextView percent;
List<String>list;
    Dialog_Try_Network dialog_try_network;
    Timer timer;int counter=0;
    TextView infos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        SetupViews();
        get_info();
        Check_vaziat();
        Retry();
    }

    private void Retry() {
        dialog_try_network=new Dialog_Try_Network();
        Dialog_Try_Network.callback_try=new Dialog_Try_Network.Callback_Try() {
            @Override
            public void onTry() {
         counter=0;
         progressBar.setProgressPercentage(counter,false);
         infos.setText(counter+"%");
         Check_vaziat();
         infos.setVisibility(View.GONE);
         dialog_try_network.dismiss();
            }
        };

    }


    private void Check_vaziat() {
        SharedPreferences sharedPref = getSharedPreferences("shared", MODE_PRIVATE);
        if(sharedPref.getInt("is_login",0)==0){
            Handle_Progress(0);
        }else {
            Handle_Progress(1);
        }
    }

    private void Request_to_Server(String number,String username) {

        StringRequest stringRequest=new StringRequest(Request.Method.POST,Urls.get_infos, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] splited=response.split("/");
                Model_Current_User.username=splited[0];
                Model_Current_User.password=splited[1];
                Model_Current_User.number=splited[2];
                Model_Current_User.coin=splited[3];
                Model_Current_User.money=splited[4];
                Model_Current_User.score_image=splited[5];
                Model_Current_User.score=splited[6];
                Model_Current_User.score_gozine=splited[7];
                Model_Current_User.score_speed=splited[8];
                Model_Current_User.mode=splited[9];
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                timer.cancel();
                dialog_try_network=new Dialog_Try_Network();
                dialog_try_network.show(getSupportFragmentManager(),"");

            }
        }){//send to server

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                param = new HashMap<>();
                param.put("key", Statics.get_info);
                param.put("number", number);
                param.put("username", username);
                return param;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy());
        requestQueue.add(stringRequest);


    }

    private void Handle_Progress(int classmode) {
        SharedPreferences sharedPref = getSharedPreferences("shared", MODE_PRIVATE);
        String username = sharedPref.getString("username", "");
        String number = sharedPref.getString("number", "");
         timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        counter+=2;
                        percent.setText(counter+"%");
                        progressBar.setProgressPercentage(counter,false);
                        if(counter==100 && classmode==0){
                            timer.cancel();
                            timer.purge();
                            Intent intent=new Intent(getApplicationContext(),Login_Logup.class);
                            startActivity(intent);
                            finish();
                        }else if(counter==50 && classmode==1){
                            Request_to_Server(number,username);
                            if(list.size()!=0){
                                Random random=new Random();
                                int x=random.nextInt(list.size());
                                infos.setText(list.get(x));
                            }
                            infos.setVisibility(View.VISIBLE);
                        }else if(counter==80 && classmode==1 && Model_Current_User.money==null){
                            timer.cancel();
                            timer.purge();
                             dialog_try_network=new Dialog_Try_Network();
                            dialog_try_network.show(getSupportFragmentManager(),"");
                        }
                        else if(counter==100 && classmode==1){
                            if(Model_Current_User.money!=null){
                            timer.cancel();
                            timer.purge();
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                                timer.cancel();
                                timer.purge();
                                dialog_try_network=new Dialog_Try_Network();
                                dialog_try_network.show(getSupportFragmentManager(),"");
                            }

                        }
                    }
                });
            }
        },100,100);

    }

    private void get_info(){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Urls.get_splash_info, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject=response.getJSONObject(i);
                        list.add(jsonObject.getString("info"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy());
        requestQueue.add(jsonArrayRequest);



    }

    private void SetupViews() {
        progressBar=findViewById(R.id.progressBar2);
        percent=findViewById(R.id.percent_show);
        infos=findViewById(R.id.infos_splash);
        list=new ArrayList<>();

    }


}