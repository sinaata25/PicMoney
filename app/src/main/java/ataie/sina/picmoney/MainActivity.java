package ataie.sina.picmoney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import ataie.sina.picmoney.adapters.NavAdapter;
import ataie.sina.picmoney.fragments.Buy;
import ataie.sina.picmoney.fragments.Credit;
import ataie.sina.picmoney.fragments.Home;
import ataie.sina.picmoney.fragments.Jam;
import ataie.sina.picmoney.fragments.Setting;
import ataie.sina.picmoney.fragments.Wanna_Exit;

public class MainActivity extends AppCompatActivity  {
    ViewPager viewPager;
    TabLayout tabLayout;
    NavAdapter navAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Setup_Views();
        Handle_Bottom_Navs();


    }

    private void Setup_Views() {
        viewPager=findViewById(R.id.pager);
        tabLayout=findViewById(R.id.tablayout);

    }

    void Handle_Bottom_Navs(){
        viewPager.setAdapter(navAdapter);
        navAdapter=new NavAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        navAdapter.addfragment(new Setting());
        navAdapter.addfragment(new Jam());
        navAdapter.addfragment(new Home());
        navAdapter.addfragment(new Buy());
        navAdapter.addfragment(new Credit());
        viewPager.setAdapter(navAdapter);
        tabLayout.setupWithViewPager(viewPager);
        Set_Tablayout_Icons();
        viewPager.setCurrentItem(2);

    }


void Set_Tablayout_Icons(){
    tabLayout.getTabAt(0).setIcon(R.drawable.settings);
    tabLayout.getTabAt(1).setIcon(R.drawable.cup);
    tabLayout.getTabAt(2).setIcon(R.drawable.house);
    tabLayout.getTabAt(3).setIcon(R.drawable.shopping);
    tabLayout.getTabAt(4).setIcon(R.drawable.wallet);
}


}