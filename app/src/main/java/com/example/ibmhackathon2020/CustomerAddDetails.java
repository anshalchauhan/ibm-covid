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

public class CustomerAddDetails extends AppCompatActivity {

    EditText add1,pno1,pcode1;
    Button addbtn;
    Register cusregister;
    DatabaseReference reff;
    String uid,name,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_add_details);

        uid=getIntent().getStringExtra("uid");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        add1=(EditText)findViewById(R.id.address_1);
        pno1=(EditText)findViewById(R.id.pin_code);
        pcode1=(EditText)findViewById(R.id.phonenum1);
        addbtn=(Button)findViewById(R.id.add_item_btn);

        cusregister = new Register();

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (add1.getText().toString().isEmpty() || pcode1.getText().toString().isEmpty() || pno1.getText().toString().isEmpty()) {
                        Toast.makeText(CustomerAddDetails.this, "Add all details", Toast.LENGTH_LONG).show();
                    } else {
                        String x = add1.getText().toString().trim();
                        String y = pno1.getText().toString().trim();
                        String z = pcode1.getText().toString().trim();

                        cusregister.setUserId(uid);
                        cusregister.setName(name);
                        cusregister.setPhone_no(z);
                        cusregister.setAddress(x);
                        cusregister.setPincode(y);
                        cusregister.setEmail(email);
                        cusregister.setPassword(password);

                        reff.child(uid).setValue(cusregister);
                        Toast.makeText(CustomerAddDetails.this, "Added Successfully", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(CustomerAddDetails.this, login_activity.class);
                        startActivity(i);
                    }
                }
                catch (Exception e){
                    Toast.makeText(CustomerAddDetails.this, "Error"+e, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}