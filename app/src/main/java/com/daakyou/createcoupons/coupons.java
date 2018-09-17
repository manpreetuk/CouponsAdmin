package com.daakyou.createcoupons;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daakyou.R;
import com.daakyou.pojo.couponinsert;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;
import java.util.UUID;

public class coupons extends AppCompatActivity {
    private EditText title,count,offer;
    private Button generate;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);
        title=(EditText)findViewById(R.id.title);
        count=(EditText)findViewById(R.id.number);
        offer=(EditText)findViewById(R.id.offer);
        generate=(Button)findViewById(R.id.generate);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Please Wait...");
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String no=count.getText().toString().trim();
            int noof=Integer.valueOf(no);
            for (int i=1;i<=noof;i++)
            {
                dialog.show();
                register(i,noof);
            }
            }
        });
    }

    private void register( final int i, final int length)
    {
        String title=this.title.getText().toString().trim();
        String count=this.count.getText().toString().trim();
        String offer=this.offer.getText().toString().trim();
        String cupponcode=generateString();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("AllCoupons").child("Valid_Coupons");
        String id=reference.push().getKey();
        couponinsert in=new couponinsert(title,offer,"active",cupponcode);
        reference.child(cupponcode).setValue(in).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                if(i==length)
                {
                    Toast.makeText(coupons.this, "New Coupons inserted", Toast.LENGTH_SHORT).show();
                    dialog.hide();
                    dialog.cancel();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(coupons.this, "Something wrong...!", Toast.LENGTH_SHORT).show();
                dialog.cancel();
                dialog.hide();
            }
        });

    }

    private String generateString() {

            String SALTCHARS = "abcdefghijklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ123456789";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 20) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
            String saltStr = salt.toString();
            return saltStr;





    }
}
