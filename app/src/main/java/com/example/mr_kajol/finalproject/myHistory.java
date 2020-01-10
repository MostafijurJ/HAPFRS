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
    ArrayList<HitoryGetSet>Histo;


    public myHistory(Context c , ArrayList<HitoryGetSet> p)
    {
        context = c;
        Histo = p;
    }

    @NonNull
    @Override
    public mViewholer onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new mViewholer(LayoutInflater.from(context).inflate(R.layout.activity_historyshow,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull mViewholer hold, int pos) {

        String Date = Histo.get(pos).getDate();
        String  pal = Histo.get(pos).getPAL();
        String weight = Histo.get(pos).getWeight();
      /*  String Height = arrayList.get(i).getHeight();
        String Calorie = arrayList.get(i).getCalorie();
*/
        hold.date.setText("This text is setted");
        hold.height.setText(Date);
        hold.weight.setText("fdgdfg");


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
            height = (TextView) itemView.findViewById(R.id.histheight);
            weight = (TextView) itemView.findViewById(R.id.histoweight);


        }

    }
}
