package ataie.sina.picmoney;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.jackandphantom.blurimage.BlurImage;


public class Game extends AppCompatActivity {
    ImageView imageView;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        SetupViews();
    }

    private void SetupViews() {
        imageView=findViewById(R.id.image_game);
    }


}