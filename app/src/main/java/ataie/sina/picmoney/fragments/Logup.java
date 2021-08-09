package ataie.sina.picmoney.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ataie.sina.picmoney.MainActivity;
import ataie.sina.picmoney.R;
import ataie.sina.picmoney.Splash_Activity;
import ataie.sina.picmoney.Statics;
import ataie.sina.picmoney.Urls;

public class Logup extends Fragment {
    View view;
    Timer timer;
    int  counter=60;
    int can_send=1;
    String activate="";
    EditText phone,username,password,verification_text;
    Button send_vdrif;
    LinearLayout layout_again;
    TextView again;String val_phone,val_username,val_pass;
    Map<String,String> param;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.logup,container,false);
        SetupViews();
        Handle_btns();

        return view;
    }


    private void SetupViews() {
        phone=view.findViewById(R.id.phone_logup);
        username=view.findViewById(R.id.username_logup);
        password=view.findViewById(R.id.newpass);
        verification_text=view.findViewById(R.id.verif_text_logup);
        send_vdrif=view.findViewById(R.id.send_verif_logup);
        layout_again=view.findViewById(R.id.sen_again_layout_logup);
        again=view.findViewById(R.id.send_again_logup);

    }

    private void Handle_btns() {
        send_vdrif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(send_vdrif.getContentDescription().equals("0")){
                if(!phone.getText().toString().equals("") && !username.getText().toString().equals("") && !password.getText().toString().equals("")){
                    if(phone.getText().toString().length()==11 && phone.getText().toString().charAt(0)=='0' ){
                        check();
                        val_phone=phone.getText().toString();
                        val_username=username.getText().toString();
                        val_pass=password.getText().toString();
                    }else{
                        Toast.makeText(getContext(),"فرمت شماره وارد شده صحیح نمیباشد",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getContext(),"لطفا همه اطلاعات را وارد کنید",Toast.LENGTH_SHORT).show();
                }
            }else {
                    if(verification_text.getText().toString().equals(activate)){
                        SharedPreferences.Editor editor = getContext().getSharedPreferences("shared",Context.MODE_PRIVATE).edit();
                        editor.putString("username",val_username);
                        editor.putString("number",val_phone);
                        editor.putInt("is_login",1);
                        editor.apply();
                        Intent intent=new Intent(getContext(), Splash_Activity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(),"کد تایید وارد شده اشتباه است",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        ////////////////


    }


    private void check(){

        StringRequest stringRequest=new StringRequest(Request.Method.POST,Urls.url_logup_check, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               if(!response.equals("0")){
                   verification_text.setVisibility(View.VISIBLE);
                   send_vdrif.setText("تایید");
                   send_vdrif.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardstyle9));
                   layout_again.setVisibility(View.VISIBLE);
                   Timer_again();
                   send_vdrif.setContentDescription("1");
                    Send_Activation_code();
               }else {
                   Toast.makeText(getContext(),"کاربری با نام کاربری یا شماره فوق در سیستم ثبت نام کرده است",Toast.LENGTH_SHORT).show();
               }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"خطا در اتصال به سرور",Toast.LENGTH_SHORT).show();
            }
        }){//send to server
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                param = new HashMap<>();
                param.put("key", Statics.key_check_user_logup);
                param.put("phone", phone.getText().toString());
                param.put("username", username.getText().toString());
                return param;
            }


        };//

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy());
        requestQueue.add(stringRequest);



    }



    void Send_Activation_code(){
        int random = new Random().nextInt(9999) + 1020;
        activate=String.valueOf(random);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,Urls.logup, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // activate=response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"خطا در اتصال به سرور",Toast.LENGTH_SHORT).show();
            }
        }){//send to server
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                param = new HashMap<>();
                param.put("key", Statics.key_activate_logup);
                param.put("number", phone.getText().toString());
                param.put("username", username.getText().toString());
                param.put("password", password.getText().toString());
                param.put("can_send", String.valueOf(can_send));
                param.put("activate", activate);
                can_send=0;
                return param;
            }


        };//

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy());
        requestQueue.add(stringRequest);




    }







    void Timer_again(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(counter!=0){
                            counter--;
                            int min=counter/60;
                            int sec=counter%60;
                            again.setText("0"+min+":"+sec);
                        }else {
                            timer.purge();
                            timer.cancel();
                            can_send=1;
                            again.setText("");
                        }

                    }
                });
            }
        }, 1000,1000);
    }




















}
