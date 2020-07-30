package com.example.ibmhackathon2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add_items extends AppCompatActivity {

    EditText iname,qty,price,desc;
    Button add_btn;
    DatabaseReference reff;
    Items add_item;
    String itemname;
    Integer qty1, price1;
    long id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);

        final String sid = getIntent().getStringExtra("sid");
        iname=(EditText) findViewById(R.id.item_name);
        qty=(EditText) findViewById(R.id.qty);
        price=(EditText) findViewById(R.id.cost);
        desc=(EditText) findViewById(R.id.desc);
        add_btn=(Button) findViewById(R.id.add_item_btn);
        reff = FirebaseDatabase.getInstance().getReference().child("Items");
        add_item = new Items();

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    id=(snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Add_items.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String iname1 = iname.getText().toString().trim();
                final String desc1 = desc.getText().toString().trim();
                String p1=price.getText().toString().trim();
                String q1=qty.getText().toString().trim();
                int p = Integer.parseInt(p1);
                int q = Integer.parseInt(q1);

                    add_item.setItemName(iname1);
                    add_item.setCost(p);
                    add_item.setQty(q);
                    add_item.setItemId((int) (id+1));
                    add_item.setDesc(desc1);
                    add_item.setSellerId(sid);
                    reff.child(String.valueOf(id+1)).setValue(add_item);

                    Toast.makeText(Add_items.this,"Item added successfully...!!",Toast.LENGTH_LONG).show();
            }
        });

    }
}