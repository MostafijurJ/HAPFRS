package com.example.mr_kajol.finalproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;
import static com.example.mr_kajol.finalproject.bmiopetation.MY_PREFS_NAME;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    String FoodName;
    Double FoodValue = 0.0, Carbo = 0.0, Fat=0.0, Protin = 0.0,TotalCal=0.0, SelectCal=0.0;
    Double CarboCount=0.0, FatCount=0.0, ProtinCount= 0.0;

    Context context;
    ArrayList<Profile> profiles;
    ArrayList<String> CalorieList;

    RecycleTest recycleTest = new RecycleTest();



    //String st = new DecimalFormat("##.##").format(cl);

    public MyAdapter(Context c , ArrayList<Profile> p)
    {
        context = c;
        profiles = p;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_recycle_dialoge,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FoodName = profiles.get(position).getBengaliName();
        holder.name.setText(FoodName);

        Carbo = profiles.get(position).getCarbohydrate();
        Fat = profiles.get(position).getFat();
        Protin = profiles.get(position).getProtein();

        FoodValue = Fat+Carbo+Protin;

        String FAT = new DecimalFormat("##.##").format(Fat);
        String CAR = new DecimalFormat("##.##").format(Carbo);
        String Pro = new DecimalFormat("##.##").format(Protin);

        String Value = "Nutrients per 100 gram\nCarbohydrate : "+CAR + "\nProtein : " +Pro + "\nFat : "+FAT;
        holder.details.setText(Value);

        Picasso.get().load(profiles.get(position).getUrl()).into(holder.FoodImage);
        holder.checkBox.setVisibility(View.VISIBLE);
        holder.onClick(position);

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    Button button;
    EditText FoodAmount;
    LinearLayout layout;


    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,details;
        ImageView FoodImage;
        CheckBox checkBox;
        Button pus;


        public MyViewHolder(final View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            details = (TextView) itemView.findViewById(R.id.tvdetails);
            FoodImage = (ImageView) itemView.findViewById(R.id.profilePic);
            checkBox = itemView.findViewById(R.id.check);
            pus = itemView.findViewById(R.id.BtnFoodAmount);
            FoodAmount = itemView.findViewById(R.id.editTExt_EnterAmount);
            layout = itemView.findViewById(R.id.Layout);

            pus.setVisibility(View.INVISIBLE);
            FoodAmount.setVisibility(View.INVISIBLE);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        pus.setVisibility(View.VISIBLE);
                        FoodAmount.setVisibility(View.VISIBLE);
                        //String FoodAmount = itemView.FoodAmount.getText().toString();
                    }
                    else{
                        pus.setVisibility(View.INVISIBLE);
                        FoodAmount.setVisibility(View.INVISIBLE);

                    }
                }
            });

        }
        public void onClick(final int position)
        {

           /* checkBox.setOnClickListener(new View.OnClickListener() {
                @Override


                public void onClick(View v) {

                    button.setVisibility(View.INVISIBLE);


                    if (isIlligible(TotalCal)) {

                        if (checkBox.isChecked()) {

                            //button.setVisibility(View.VISIBLE);

                            TotalCal += FoodValue;
                            CarboCount += Carbo;
                            FatCount += Fat;
                            ProtinCount += Protin ;
                            String TOT = new DecimalFormat("##.##").format(TotalCal);

                            Toast.makeText(context, TOT + " Calorie is Selected.", Toast.LENGTH_SHORT).show();

                        } else {
                            TotalCal = TotalCal - FoodValue;
                            CarboCount -= Carbo;
                            FatCount -= Fat;
                            ProtinCount -= Protin ;

                            if (TotalCal < 0.0)
                                TotalCal = 0.0;
                            if (CarboCount < 0.0)
                                CarboCount = 0.0;
                            if (FatCount < 0.0)
                                FatCount = 0.0;
                            if (ProtinCount < 0.0)
                                ProtinCount = 0.0;
                        }
                    }else{
                        if(checkBox.isChecked()== false){
                            TotalCal = TotalCal - FoodValue;
                            CarboCount -= Carbo;
                            FatCount -= Fat;
                            ProtinCount -= Protin ;

                            if (TotalCal < 0.0)
                                TotalCal = 0.0;
                            if (CarboCount < 0.0)
                                CarboCount = 0.0;
                            if (FatCount < 0.0)
                                FatCount = 0.0;
                            if (ProtinCount < 0.0)
                                ProtinCount = 0.0;
                        }

                        checkBox.setEnabled(false);

                    }
                }
            });*/

         /*   button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // openDialog();
                    Toast.makeText(context,   " Calorie is Selected.", Toast.LENGTH_SHORT).show();


                }
            });*/
        }



       /* public void openDialog() {
            DialogeClass exampleDialog = new DialogeClass();
           // exampleDialog.show(getSupportFragmentManager(), "example dialog");
        }
*/





        boolean isIlligible(double totalClorie){
            ///Carb = 45-60, Fat = 20-30, Protin 10-35;
            //Catch data from RecycleTEst Activity
            String Food = recycleTest.getRequiredCalorie();

            Double reqFood = Double.parseDouble(Food);
            Double CarboLow,CarboHigh,FatLow,FatHigh,ProtinLow,ProtinHigh;

            CarboLow = reqFood * 0.45;
            CarboHigh = reqFood * 0.60;
            FatLow = reqFood * 0.20;
            FatHigh = reqFood * 0.30;
            ProtinLow = reqFood * 0.10;
            ProtinHigh = reqFood * 0.35;


            if (reqFood < totalClorie)
                return false;
           /* if (CarboHigh < c)
                return false;
            if (FatHigh < f)
                return false;
            if (ProtinHigh < p)
                return false;*/

            return true;
        }
    }
}
