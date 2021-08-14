package ataie.sina.picmoney.fragments;

import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ataie.sina.picmoney.R;
import ataie.sina.picmoney.Statics;
import ataie.sina.picmoney.Urls;
import ataie.sina.picmoney.models.Model_Current_User;

public class Credit extends Fragment {
    TextView money,coin;
    EditText carnum,money_want;
    Button bardasht;
    View view;
    String time;
    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=inflater.inflate(R.layout.credit,container,false);
         SetUpViews();
         Sets();
         Handle_Click();
        return view;
    }

    private void Handle_Click() {
        bardasht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!money_want.getText().toString().equals("") || !carnum.getText().toString().equals("")){
                    int money_wanted = Integer.parseInt(money_want.getText().toString());
                int current_money = Integer.parseInt(Model_Current_User.money);
                if (Integer.parseInt(Model_Current_User.money) >= 30000) {
                    if (money_wanted > current_money || money_wanted <= 0) {
                        Toast.makeText(getContext(), "مبلغ وارد شده مجاز نمیباشد", Toast.LENGTH_SHORT).show();
                    } else {
                        Req_Money();
                    }

                } else {
                    Dialog_Low_Money dialog_low_money = new Dialog_Low_Money();
                    dialog_low_money.show(getParentFragmentManager(), "");
                }
            }
            }
        });



    }

    private void Req_Money() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Urls.url_req_money, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null){
                    Dialog_Success_Money dialog_success_money=new Dialog_Success_Money();
                    dialog_success_money.show(getParentFragmentManager(),"");
                    Model_Current_User.money=response;
                    money.setText(Model_Current_User.money+"تومان");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"خطا در اتصال به سرور",Toast.LENGTH_SHORT).show();
            }
        }){//
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param;
                param = new HashMap<>();
                param.put("key", Statics.key_reqmoney);
                param.put("number",Model_Current_User.number);
                param.put("username",Model_Current_User.username);
                param.put("money",money_want.getText().toString());
                param.put("card_num",carnum.getText().toString());
                param.put("date",time );
                return param;
            }
        };//

        RequestQueue requestQueue= Volley.newRequestQueue(getContext());
        stringRequest.setRetryPolicy(new DefaultRetryPolicy());
        requestQueue.add(stringRequest);


    }

    private void Sets() {
        money.setText(Model_Current_User.money+"تومان");
        coin.setText(Model_Current_User.coin);
        Date currentTime = Calendar.getInstance().getTime();
        time=String.valueOf(currentTime);

    }

    private void SetUpViews() {
     money=view.findViewById(R.id.show_money_credit);
        coin=view.findViewById(R.id.show_coin_credit);
        bardasht=view.findViewById(R.id.send_money);
        carnum=view.findViewById(R.id.cardnum);
        money_want=view.findViewById(R.id.money_want);

    }

}
