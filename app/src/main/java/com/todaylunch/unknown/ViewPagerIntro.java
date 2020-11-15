package com.todaylunch.unknown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class ViewPagerIntro extends AppCompatActivity {

    private ArrayList<ViewPagerObject> arrayList;
    private ViewPager2 vp;
    private ViewPagerAdapter adapter;
    private DotsIndicator indicator;
    private ImageView previousButton, nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_view_pager_intro);

        previousButton = findViewById(R.id.img_viewpager_previous);
        nextButton = findViewById(R.id.img_viewpager_next);

        arrayList = new ArrayList<>();
        addValue();

        indicator = findViewById(R.id.dots_viewpager);
        vp = findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter(this, arrayList);
        vp.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        vp.setAdapter(adapter);
        indicator.setViewPager2(vp);


        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }//onCreate

    private void addValue() {

        arrayList.add(new ViewPagerObject(R.color.colorNaturalGrey, R.drawable.viewpager_1, getResources().getString(R.string.viewpager_0)));
        arrayList.add(new ViewPagerObject(R.color.colorNaturalGrey, R.drawable.viewpager_1, getResources().getString(R.string.viewpager_1)));
        arrayList.add(new ViewPagerObject(R.color.colorNaturalGrey, R.drawable.viewpager_2, getResources().getString(R.string.viewpager_2)));
        arrayList.add(new ViewPagerObject(R.color.colorNaturalGrey, R.drawable.viewpager_3, getResources().getString(R.string.viewpager_3)));
        arrayList.add(new ViewPagerObject(R.color.colorNaturalGrey, R.drawable.viewpager_4, getResources().getString(R.string.viewpager_4)));
        arrayList.add(new ViewPagerObject(R.color.colorNaturalGrey, R.drawable.viewpager_5, getResources().getString(R.string.viewpager_5)));
        arrayList.add(new ViewPagerObject(R.color.colorNaturalGrey, R.drawable.viewpager_6, getResources().getString(R.string.viewpager_6)));
        arrayList.add(new ViewPagerObject(R.color.colorNaturalGrey, R.drawable.viewpager_7, getResources().getString(R.string.viewpager_7)));
        arrayList.add(new ViewPagerObject(R.color.colorNaturalGrey, R.drawable.viewpager_1, getResources().getString(R.string.viewpager_8)));

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleManager.updateResources(newBase));
    }
}
