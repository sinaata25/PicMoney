package ataie.sina.picmoney.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

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

import ataie.sina.picmoney.R;
import ataie.sina.picmoney.Statics;
import ataie.sina.picmoney.Urls;
import ataie.sina.picmoney.models.Model_Current_User;

public class Change_Pass extends DialogFragment {
    View view;
    EditText thispass,newpass;
    TextView ok;

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.change_pass,container,false);
        SetUpViews();
        Handle_Click();
        return view;
    }

    private void Handle_Click() {
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(thispass.getText().toString().equals("") || newpass.getText().toString().equals("") ){
                    Toast.makeText(getContext(),"همه اطلاعات را وارد کنید",Toast.LENGTH_SHORT).show();
                }else {
                    if(thispass.getText().toString().equals(Model_Current_User.password)){
                        Request_to_Server();
                    }else {
                        Toast.makeText(getContext(),"رمز عبور فعلی اشتباه است",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }

    private void Request_to_Server() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.url_change_password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Model_Current_User.password=newpass.getText().toString();
                dismiss();
                Toast.makeText(getContext(),"رمز عبور با موفقیت تغییر یافت",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"خطا در تغییر رمز عبور",Toast.LENGTH_SHORT).show();
            }
        }){
            Map<String,String> param;
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                param = new HashMap<>();
                param.put("key", Statics.change_pass);
                param.put("number", Model_Current_User.number);
                param.put("password", newpass.getText().toString());
                return param;
            }

        };
        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy());
        requestQueue.add(stringRequest);


    }


    private void SetUpViews() {
        thispass=view.findViewById(R.id.edtthispass);
        newpass=view.findViewById(R.id.edtnewpass);
        ok=view.findViewById(R.id.ok_change_pass);
    }






}
