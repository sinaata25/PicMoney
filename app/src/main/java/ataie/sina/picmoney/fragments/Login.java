package ataie.sina.picmoney.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import ataie.sina.picmoney.R;
import ataie.sina.picmoney.Statics;
import ataie.sina.picmoney.Urls;
import ataie.sina.picmoney.models.Model_Current_User;

public class Login extends Fragment {
    View view;
    EditText verification_text,phone_txt,password_txt;
    TextView new_account;
    Button send_verif_btn;
    String verif_code;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.login,container,false);
        Setupviwes();
        Handle_btn();
        return view;
    }

    private void Handle_btn() {
        send_verif_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!password_txt.getText().toString().equals("") && !phone_txt.getText().toString().equals("")){
                    String content=send_verif_btn.getContentDescription().toString();
                    if(content.equals("0")){
                            Ask_server();
                    }else {
                            if(verification_text.equals(verif_code)){

                            }else{

                            }
                    }

                }else {
                    Toast.makeText(getContext(),"لطفا همه اطلاعات را وارد کنید",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Ask_server(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST,Urls.url_login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("no")){
                    Toast.makeText(getContext(),"شماره همراه یا رمز عبور اشتباه است",Toast.LENGTH_SHORT).show();
                }else {
                    Set_Model_Function(response);
                    verification_text.setVisibility(View.VISIBLE);
                    send_verif_btn.setText("تایید");
                    send_verif_btn.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.cardstyle9));
                    send_verif_btn.setContentDescription("1");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"مشکل در اتصال به سرور",Toast.LENGTH_SHORT).show();
            }
        }){//send to server
            Map<String,String> param;
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                param=new HashMap<>();
                param.put("key", Statics.key_login);
                param.put("number",phone_txt.getText().toString());
                param.put("password",password_txt.getText().toString());
                return param;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void Setupviwes() {
        send_verif_btn=view.findViewById(R.id.sen_verif_buttonn);
        new_account=view.findViewById(R.id.new_account_textview);
        verification_text=view.findViewById(R.id.verification_text);
        password_txt=view.findViewById(R.id.editTextTextPassword_login);
        phone_txt=view.findViewById(R.id.editTextPhone_login);
    }


    void Set_Model_Function(String response){
    String[] splited=response.split("/");
        verif_code=splited[0];
        Model_Current_User.username=splited[1];
        Model_Current_User.password=splited[2];
        Model_Current_User.number=splited[3];
        Model_Current_User.coin=splited[4];
        Model_Current_User.money=splited[5];
        Model_Current_User.score_image=splited[6];
        Model_Current_User.score=splited[7];
        Model_Current_User.score_gozine=splited[8];
        Model_Current_User.score_speed=splited[9];
        Model_Current_User.mode=splited[10];
    }





}
