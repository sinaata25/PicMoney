package ataie.sina.picmoney.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Dialog_Coin_Money extends DialogFragment {
    View view;
    TextView textView,txt,taeed;
    int can_buy=0;
   public static Callback_Changed callback_changed;
String s;
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable  Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialog_coin_money,container,false);
        SetUpViews();
        Sets();
        Handle_Click();
        return view;
    }



    private void Handle_Click() {
        /////////////////////
textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dismiss();
    }
});
////////////////////////
        taeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(can_buy>=1){
                 req();
                }
            }
        });


    }

    private void SetUpViews() {
   textView=view.findViewById(R.id.taeed_dialog);
   txt=view.findViewById(R.id.text_dialog);
   taeed=view.findViewById(R.id.yes_dialog);

    }

    void req(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.url_money_coin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String[] splited=response.split("/");
                Model_Current_User.money=splited[1];
                Model_Current_User.coin=splited[0];
                if(callback_changed!=null){
                    callback_changed.changed();
                }

               dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Dialog_Info dialog_info=new Dialog_Info();
            dialog_info.show(getParentFragmentManager(),"خطا در اتصال به سرور!");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param;
                param = new HashMap<>();
                param.put("key", Statics.key_money_coin);
                param.put("number",Model_Current_User.number);
                param.put("username",Model_Current_User.username);
                return param;
            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy());
        requestQueue.add(stringRequest);

    }


    private void Sets() {
        int mon=Integer.parseInt(Model_Current_User.money);
        can_buy=mon/4000;
        if(can_buy>=1){
            s="شما میتونید اعتبار فعلیتون رو به "+can_buy+" سکه تبدیل کنید";
            taeed.setVisibility(View.VISIBLE);
        }
        else {
            s="شما اعتبار کافی برای تبدیل به سکه ندارید!";
        }

        txt.setText(s);
    }


    public interface Callback_Changed{
        void changed();
    }





}
