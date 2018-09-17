package com.daakyou;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.daakyou.Fragments.all_coupons;
import com.daakyou.Fragments.redeemcp;
import com.daakyou.createcoupons.coupons;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements redeemcp.OnFragmentInteractionListener, all_coupons.OnFragmentInteractionListener {
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        


        
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    private void swichfrag(Fragment fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.ct,fragment,fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    public void actions(View view) {
        switch (view.getId())
        {
            case R.id.generate:
                Intent in=new Intent(getApplicationContext(),coupons.class);
                startActivity(in);
            break;
            case R.id.redeem:
                swichfrag(new redeemcp());
             break;
            case R.id.allcp:
                swichfrag(new all_coupons());
            break;
            case R.id.logout:
                Toast.makeText(this, "Under Maintenance", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
