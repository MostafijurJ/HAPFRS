package com.example.mr_kajol.finalproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class myHistory extends RecyclerView.Adapter<myHistory.mViewholer> {


    Context context;
    ArrayList<HitoryGetSet>arrayList;


    public myHistory(Context c , ArrayList<HitoryGetSet> p)
    {
        context = c;
        arrayList = p;
    }

    @NonNull
    @Override
    public mViewholer onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new mViewholer(LayoutInflater.from(context).inflate(R.layout.historyshow,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull mViewholer hold, int i) {

        String Date = arrayList.get(i).getDate();
        String pal = arrayList.get(i).getPAL();
        String weight = arrayList.get(i).getWeight();
        String Height = arrayList.get(i).getHeight();
        String Calorie = arrayList.get(i).getCalorie();

        hold.date.setText(Date);
        hold.height.setText(Height);
        hold.weight.setText(weight);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class mViewholer extends RecyclerView.ViewHolder {
        TextView pal, height, weight,cal, date;

        public mViewholer(final View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.histdate);
            height = (TextView) itemView.findViewById(R.id.histdate);
            weight = (TextView) itemView.findViewById(R.id.histoweight);


        }
    }
}
