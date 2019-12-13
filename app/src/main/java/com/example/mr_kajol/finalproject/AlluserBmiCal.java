package com.example.mr_kajol.finalproject;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

public class AlluserBmiCal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    android.support.v7.widget.Toolbar toolbar;
    NavigationView navigationView;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alluser_bmi_cal);

        drawerLayout=findViewById(R.id.drawableId);
        toolbar=findViewById(R.id.Toolbar);
         navigationView=findViewById(R.id.navigationId);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);



        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.nv, R.string.nn);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        /*try{

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }catch (NullPointerException e) { }
*/



}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
        {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {



       if(item.getItemId()==R.id.homeMenuId)
        {
            Intent intent=new Intent(this,UserView.class);
            startActivity(intent);
        }




        return false;
    }
}
