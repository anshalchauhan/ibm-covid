package com.example.ibmhackathon2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SellerAddDetails extends AppCompatActivity {
    EditText loc, sname, pno2, pcode2;
    Button addbtn2;
    AdmRegister admregister;
    DatabaseReference reff;
    String sid,name,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_add_details);


        sid = getIntent().getStringExtra("sid");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        reff = FirebaseDatabase.getInstance().getReference().child("Seller");
        loc = (EditText) findViewById(R.id.location1);
        pno2 = (EditText) findViewById(R.id.pin_code1);
        pcode2 = (EditText) findViewById(R.id.phonenum2);
        sname = (EditText) findViewById(R.id.shop_name);
        addbtn2 = (Button) findViewById(R.id.add_item_btn2);

        admregister = new AdmRegister();

        addbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (loc.getText().toString().isEmpty() || pcode2.getText().toString().isEmpty() || pno2.getText().toString().isEmpty() || sname.getText().toString().isEmpty()) {
                        Toast.makeText(SellerAddDetails.this, "Add all details", Toast.LENGTH_LONG).show();
                    } else {
                        final String w = sname.getText().toString().trim();
                        final String x = loc.getText().toString().trim();
                        final String y = pno2.getText().toString().trim();
                        final String z = pcode2.getText().toString().trim();

                        admregister.setSellerId(sid);
                        admregister.setOwnerName(name);
                        admregister.setShopName(w);
                        admregister.setLocation(x);
                        admregister.setPinCode(y);
                        admregister.setPhoneNo(z);
                        admregister.setEmailid(email);
                        admregister.setPassword(password);

                       // Toast.makeText(SellerAddDetails.this, "Added Successfully"+w, Toast.LENGTH_LONG).show();
                        reff.child(sid).setValue(admregister);
                        Toast.makeText(SellerAddDetails.this, "Added Successfully", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SellerAddDetails.this, login_activity.class);
                        startActivity(i);
                    }
                } catch (Exception e) {
                    Toast.makeText(SellerAddDetails.this, "Error" + e, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}