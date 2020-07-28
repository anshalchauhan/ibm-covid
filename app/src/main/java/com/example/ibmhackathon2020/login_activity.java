package com.example.ibmhackathon2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_activity extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreateBtn,forgotTextLink;
    ProgressBar progressBar;
    String userID;
    FirebaseAuth fAuth;
    Button registerButton;
    DatabaseReference reff,reff2;
    DataSnapshot datasnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        mEmail = findViewById(R.id.name1);
        mPassword = findViewById(R.id.password);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.login_button1);
        registerButton = findViewById(R.id.register1);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                reff = FirebaseDatabase.getInstance().getReference().child("Member");
                reff2 = FirebaseDatabase.getInstance().getReference().child("Seller");

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                //progressBar.setVisibility(View.VISIBLE);

                // authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userID = fAuth.getCurrentUser().getUid();
                            reff.orderByChild("userId").equalTo(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        Toast.makeText(login_activity.this,  "Logged in Successfully as Customer", Toast.LENGTH_SHORT).show();
                                        Intent j= new Intent(getApplicationContext(), MainActivity.class);
                                        j.putExtra("uid",userID);
                                        startActivity(j);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(login_activity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            });
                            reff2.orderByChild("sellerId").equalTo(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        Toast.makeText(login_activity.this,  "Logged in Successfully as Seller", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), Admindashboard.class);
                                        i.putExtra("sid",userID);
                                        startActivity(i);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(login_activity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }else {
                            Toast.makeText(login_activity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //  progressBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_activity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}