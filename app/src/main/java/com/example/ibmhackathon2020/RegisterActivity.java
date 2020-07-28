package com.example.ibmhackathon2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    String userID;
    EditText edname, edemail, edpasswd, edrpasswd;
    Button reg;
    DatabaseReference reff,reff2;
    Register cusregister;
    AdmRegister admregister;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    RadioButton cusrad,admrad;
    RadioGroup radioid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edname = (EditText) findViewById(R.id.name1);
        edemail = (EditText) findViewById(R.id.email1);
        edpasswd = (EditText) findViewById(R.id.password1);
        edrpasswd = (EditText) findViewById(R.id.rpassword);
        reg = (Button) findViewById(R.id.register_button1);
        cusrad = (RadioButton)findViewById(R.id.radio_customer);
        admrad = (RadioButton)findViewById(R.id.radio_seller);
        radioid = (RadioGroup)findViewById(R.id.radio_id);

        cusregister = new Register();
        admregister = new AdmRegister();
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        reff2 = FirebaseDatabase.getInstance().getReference().child("Seller");

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

       /* reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cusregister.setName(edname.getText().toString().trim());
                cusregister.setEmail(edemail.getText().toString().trim());
                cusregister.setPassword(edpasswd.getText().toString().trim());

                reff.push().setValue(cusregister);
                Toast.makeText(RegisterActivity.this,"Success",Toast.LENGTH_LONG).show();
            }
        });*/
      /* if(radioid.getCheckedRadioButtonId()==-1){
           Toast.makeText(getApplicationContext(),"Choose any one account type",Toast.LENGTH_SHORT).show();
       }
       else{
           int selectedId = radioid.getCheckedRadioButtonId();
       }*/

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edemail.getText().toString().trim();
                final String password = edpasswd.getText().toString().trim();
                final String name = edname.getText().toString().trim();
                final String rpassword = edrpasswd.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    edemail.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    edpasswd.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6) {
                    edrpasswd.setError("Password Must be >= 6 Characters");
                    return;
                }

                if (!password.equals(rpassword)) {
                    edrpasswd.setError("Password");
                    return;
                }

                // progressBar.setVisibility(View.VISIBLE);

                // register the user in firebas



                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // send verification link

                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(RegisterActivity.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                }
                            });

                            userID = fAuth.getCurrentUser().getUid();

                            if(cusrad.isChecked()) {
                                cusregister.setUserId(userID);
                                cusregister.setName(name);
                                cusregister.setPhone_no(0);
                                cusregister.setAddress("null");
                                cusregister.setPincode(0);
                                cusregister.setEmail(email);
                                cusregister.setPassword(password);
                                reff.child(userID).setValue(cusregister);
                                Toast.makeText(RegisterActivity.this, "Registered as Customer.", Toast.LENGTH_SHORT).show();
                            }
                            else if(admrad.isChecked()){
                               admregister.setSellerId(userID);
                               admregister.setOwnerName(name);
                               admregister.setShopName("null");
                               admregister.setLocation("null");
                               admregister.setPinCode(0);
                               admregister.setPhoneNo(0);
                               admregister.setEmailid(email);
                               admregister.setPassword(password);
                                reff2.child(userID).setValue(admregister);

                                Toast.makeText(RegisterActivity.this, "Registered as Seller.", Toast.LENGTH_SHORT).show();
                            }

                           /* DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",fullName);
                            user.put("email",email);
                            user.put("password",);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));*/

                        } else {
                            Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }

    public void checkButton(View view) {
    }
}
