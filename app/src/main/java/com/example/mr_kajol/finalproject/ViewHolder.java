package com.example.mr_kajol.finalproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(View itemView) {
        super(itemView);

        mView = itemView;


    }

    //set details to recycler view row  BengaliName,,Fat,;
    // String Carbohydrate, String Protein, String Fat

    public void setDetails(Context ctx,  String BengaliName, String url,String EnglishName, Double Fat, Double Carbohydrate, Double Protein ){
        //Views
        TextView mTitleTv = mView.findViewById(R.id.rTitleTv);
        TextView mDetailTv = mView.findViewById(R.id.rDescriptionTv);
        ImageView mImageIv = mView.findViewById(R.id.rImageView);

        //set data to views
        String FAT = new DecimalFormat("##.##").format(Fat);
        String CAR = new DecimalFormat("##.##").format(Carbohydrate);
        String Pro = new DecimalFormat("##.##").format(Protein);

        String Value = "Carbohydrate : "+CAR + "\nProtein : " +Pro + "\nFat : "+FAT;

        mTitleTv.setText(BengaliName);
        mDetailTv.setText(Value);

        Picasso.get().load(url).into(mImageIv);
    }
}
