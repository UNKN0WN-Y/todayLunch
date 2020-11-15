package com.todaylunch.unknown;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerHolder> {

    private ArrayList<ViewPagerObject> arrayList;
    private Context context;
    private ButtonDrawableUtil buttonDrawableUtil;
    private TypefaceUtil textTypefaceUtil;

    public ViewPagerAdapter() {
    }

    public ViewPagerAdapter(Context context, ArrayList<ViewPagerObject> arrayList) {

        this.arrayList = arrayList;
        this.context = context;
        buttonDrawableUtil = new ButtonDrawableUtil(context);
        textTypefaceUtil = new TypefaceUtil(context);

    }

    @NonNull
    @Override
    public ViewPagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        System.out.println("CreateViewHolder");

        View view = LayoutInflater.from(context).inflate(R.layout.viewpager_1, parent, false);
        ViewPagerHolder viewHolder = new ViewPagerHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerHolder holder, int position) {

        System.out.println("BIndViewHolder");

        holder.layout.setBackgroundResource(arrayList.get(position).getPagerColor());
        //holder.layout.setBackground (arrayList.get(position).getPagerColor());
        holder.imageView.setImageResource(arrayList.get(position).getImgResource());
        holder.textView.setText(arrayList.get(position).getAppText());
        holder.textView.setTypeface(textTypefaceUtil.getTypeface(0));

        holder.btn.setBackground(buttonDrawableUtil.getDrawable(6));

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                ((Activity) context).finish();

            }
        });


    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }
}

class ViewPagerHolder extends RecyclerView.ViewHolder {

    public ConstraintLayout layout;
    public ImageView imageView;
    public TextView textView;
    public Button btn;

    public ViewPagerHolder(@NonNull View itemView) {
        super(itemView);

        layout = itemView.findViewById(R.id.ll_viewpager);
        btn = itemView.findViewById(R.id.btn_skip);
        imageView = itemView.findViewById(R.id.img_viewpager);
        textView = itemView.findViewById(R.id.tv_viewpager);

    }
}