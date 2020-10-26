package com.todaylunch.unknown;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Fragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private SQLiteOpenHelperIcon dbHelper = null;
    private FloatingActionButton fab1, fab2, fab3;
    private ArrayList<ListObject3> iconArrayList;
    private ArrayList arrayListTextView, arrayListImageView;
    protected boolean fabSwitch = true;
    private Animation fab_open, fab_close, fab_rotate, fab_rotate_reverse;
    private CustomProgressDialog customProgressDialog;
    private TextView tvTitle;
    private TypefaceUtil typefaceUtil;
    private int fontNumber;
    private ImageView imageViewQuestion;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        init_value();
        View view = inflater.inflate(R.layout.fragment_fragment1,container, false);

        customProgressDialog = new CustomProgressDialog(getContext());
        customProgressDialog.setProgressDialog();

        arrayListTextView = new ArrayList();
        arrayListImageView = new ArrayList();
        iconArrayList = new ArrayList<>();
        fontNumber = MainActivity.FONT_NUMBER;

        typefaceUtil = new TypefaceUtil(getContext());
        tvTitle = view.findViewById(R.id.tv_fragment1_main);
        tvTitle.setTypeface(typefaceUtil.getTypeface(fontNumber));

        fab1 = (FloatingActionButton) view.findViewById(R.id.fab_frg1);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab_frg2);
        fab3 = (FloatingActionButton) view.findViewById(R.id.fab_frg3);
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        fab_rotate = AnimationUtils.loadAnimation(getContext(), R.anim.fab_rotate);
        fab_rotate_reverse = AnimationUtils.loadAnimation(getContext(), R.anim.fab_reverse_rotate);

        //fab1.setRippleColor(MainActivity.COLOR_NUMBER);
        //fab1.setBackgroundColor(MainActivity.COLOR_NUMBER);
        //fab1.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary_dark));
        fab1.setBackgroundTintList(ColorStateList.valueOf(MainActivity.COLOR_NUMBER));
        fab2.setBackgroundTintList(ColorStateList.valueOf(MainActivity.COLOR_NUMBER));
        fab3.setBackgroundTintList(ColorStateList.valueOf(MainActivity.COLOR_NUMBER));

        imageViewQuestion = view.findViewById(R.id.img_fragment1_question);

        load_value();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        FragmentAdapter mAdapter = new FragmentAdapter(getActivity(), iconArrayList);
        recyclerView.setAdapter(mAdapter);

        customProgressDialog.offProgressDialog();


        fabAnimation();







        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fabAnimation();
            }
        });

        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Click", "fab2가 클릭되었습니다");

                Intent intent1 = new Intent(getActivity(), AddMenu.class);
                intent1.putExtra("AddMenu", 0);
                intent1.putExtra("TITLE", "Nothing");
                startActivity(intent1);
                fabAnimation();
            }
        });

        //여기를 Extra형식으로 해결할 것.
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Click", "fab3이 클릭되었습니다.");

                Intent intent2 = new Intent(getActivity(), MenuAdjust.class);
                intent2.putExtra("MenuAdjust", 0);
                startActivity(intent2);
                fabAnimation();
            }
        });

        imageViewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ViewPager Activity intent.
                Intent intent = new Intent(getContext(), ViewPagerIntro.class);
                startActivity(intent);
                ((Activity) getContext()).finish();
                Log.d("Image Click : ", "ViewPager Intent");
            }
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void fabAnimation() {

        if (fabSwitch) {

            fab1.startAnimation(fab_rotate_reverse);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);

            fab2.setClickable(false);
            fab3.setClickable(false);

            fabSwitch = false;


        } else {

            fab1.startAnimation(fab_rotate);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);

            fab2.setClickable(true);
            fab3.setClickable(true);


            fabSwitch = true;
        }


    }

    private void init_value() {
        dbHelper = new SQLiteOpenHelperIcon(getActivity());
    }

    private void load_value() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(MySQLite.SQL_SELECT2, null);

        cursor.moveToFirst();
        for (int i=0; i < 9; i++) {

            int menuNumber = cursor.getInt(0);
            String menuTitle = cursor.getString(1);
            int menuImageNumber = cursor.getInt(2);

            ListObject3 menuObject = new ListObject3(menuNumber, menuTitle, menuImageNumber);

            iconArrayList.add(menuObject);

            cursor.moveToNext();

        }
        cursor.close();
        db.close();
        Log.d("close", "fragment1 db closed");

    }

}