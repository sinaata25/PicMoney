package ataie.sina.picmoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import ataie.sina.picmoney.adapters.NavAdapter;
import ataie.sina.picmoney.adapters.NavAdapter_Login_Logup;
import ataie.sina.picmoney.fragments.Buy;
import ataie.sina.picmoney.fragments.Home;
import ataie.sina.picmoney.fragments.Login;
import ataie.sina.picmoney.fragments.Logup;
import ataie.sina.picmoney.fragments.Setting;

public class Login_Logup extends AppCompatActivity {
        NavAdapter_Login_Logup navAdapter;
        ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_logup);
        SetupViews();
        Handle_Bottom_Navs();


    }

    private void SetupViews() {
        viewPager=findViewById(R.id.viewpagerlogs);
    }
    void Handle_Bottom_Navs(){
        viewPager.setAdapter(navAdapter);
        navAdapter=new NavAdapter_Login_Logup(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        navAdapter.addfragment(new Login());
        navAdapter.addfragment(new Logup());
        viewPager.setAdapter(navAdapter);
      //  viewPager.setCurrentItem(0);

    }

}