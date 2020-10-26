package com.todaylunch.unknown;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyAdapter_Fragment extends RecyclerView.Adapter<MyViewHolder> {


    private ArrayList<ListObject> filterArrayList;
    private ArrayList<ListObject> arrayList;
    private ArrayList<String> arrayListIcon;
    public Context context;
    private SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private Date date = new Date();
    private TypefaceUtil typefaceUtil;
    private int typefaceNumber, btnNumber;
    private ButtonDrawableUtil btnUtil;


    public MyAdapter_Fragment() {}

    public MyAdapter_Fragment(ArrayList<ListObject> mList, ArrayList<String> arrayListIcon, Context mContext) {
        this.arrayList = mList;
        this.context = mContext;
        this.arrayListIcon = arrayListIcon;
        filterArrayList = new ArrayList<>();
        filterArrayList.addAll(arrayList);
        typefaceUtil = new TypefaceUtil(mContext);
        typefaceNumber = MainActivity.FONT_NUMBER;
        btnUtil = new ButtonDrawableUtil(mContext);
        btnNumber = MainActivity.BACKGROUND_NUMBER;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_resource, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        long longDate = arrayList.get(position).getStrDate();
        final int Int1 = arrayList.get(position).getMenu1();
        final int Int2 = arrayList.get(position).getMenu2();
        final String strTitle = arrayList.get(position).getStrTitle();
        final String strLink = arrayList.get(position).getStrLink();

        holder.strDate.setTypeface(typefaceUtil.getTypeface(typefaceNumber));
        holder.menu1.setTypeface(typefaceUtil.getTypeface(typefaceNumber));
        holder.menu2.setTypeface(typefaceUtil.getTypeface(typefaceNumber));
        holder.strTitle.setTypeface(typefaceUtil.getTypeface(typefaceNumber));
        holder.btn_card_adjust.setTypeface(typefaceUtil.getTypeface(typefaceNumber));
        holder.btn_card_link.setTypeface(typefaceUtil.getTypeface(typefaceNumber));
        holder.btn_card_choice.setTypeface(typefaceUtil.getTypeface(typefaceNumber));

        holder.btn_card_choice.setBackground(btnUtil.getDrawable(btnNumber));
        holder.btn_card_link.setBackground(btnUtil.getDrawable(btnNumber));

        holder.strDate.setText(simpleFormatter.format(longDate));
        holder.menu1.setText(arrayListIcon.get(Int1 - 1));
        holder.menu2.setText(arrayListIcon.get(Int2 - 1));
        holder.strTitle.setText(strTitle);

        holder.btn_card_adjust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clicked on adjust button
                //intent / bundle

                String strTitle = arrayList.get(position).getStrTitle();
                Intent intent1 = new Intent(context, AddMenu.class);

                intent1.putExtra("LINK", strLink);
                intent1.putExtra("TITLE", strTitle);
                intent1.putExtra("MENU1", Int1);
                intent1.putExtra("MENU2", Int2);

                context.startActivity(intent1);
            }
        });

        holder.btn_card_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "선택되었습니다.", Toast.LENGTH_SHORT).show();
                String strTitle = arrayList.get(position).getStrTitle();
                SQLiteOpenHelperMain dbHelper = new SQLiteOpenHelperMain(context);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                String updateDb = MySQLite.SQL_UPDATE
                        + MySQLite.ICOL_NAME1 + " = " + date.getTime() + " WHERE " + MySQLite.ICOL_NAME4 + " = '" + strTitle + "'";
                db.execSQL(updateDb);
                //clicked on choice button(formatter to yyyy-MM-dd, today info save)
                db.close();
            }
        });

        holder.btn_card_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clicked on link button

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strLink));
                    context.startActivity(intent);
                } catch (RuntimeException e) {
                    Toast.makeText(context, "링크를 확인해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
        //while arrayList is not null, return arrayList.size().
        //while arrayList is null, return zero.
    }

    public void filter(String charText) {

        //arrayList : 전달받은 전체 값
        //filterArrayList : 값을 옮겨담아서 반영하는 리스트

        charText = charText.toLowerCase(Locale.getDefault());
        arrayList.clear();

        if (charText.length() == 0) {
            arrayList.addAll(filterArrayList);
        } else {
            for (ListObject object : filterArrayList) {

                String text = object.getStrTitle();
                if (text.toLowerCase().contains(charText)) {
                    arrayList.add(object);
                }

            }
        }

        notifyDataSetChanged();

    }

}

class MyViewHolder extends RecyclerView.ViewHolder {


    protected TextView strDate;
    protected TextView menu1;
    protected TextView menu2;
    protected TextView strTitle;
    protected Button btn_card_link, btn_card_choice, btn_card_adjust;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        this.strDate = (TextView)itemView.findViewById(R.id.tv_card_date);
        this.menu1 = (TextView)itemView.findViewById(R.id.tv_card_bigmenu);
        this.menu2 = (TextView)itemView.findViewById(R.id.tv_card_smallmenu);
        this.strTitle = (TextView)itemView.findViewById(R.id.tv_card_store);
        this.btn_card_choice = (Button)itemView.findViewById(R.id.btn_card_choice);
        this.btn_card_link = (Button)itemView.findViewById(R.id.btn_card_link);
        this.btn_card_adjust = (Button)itemView.findViewById(R.id.btn_card_adjust);

    }
}