package com.example.ibmhackathon2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibmhackathon2020.Model.Cart;
import com.example.ibmhackathon2020.Model.ItemCategory;
import com.example.ibmhackathon2020.ViewHolder.ItemCategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<ItemCategory, ItemCategoryViewHolder> adapter;
    DatabaseReference reference;
    TextView txtitem,txtcost,txtdesc;
    EditText enterqty;
    Button btn1,btn2;
    DatabaseReference reff;
    Cart cartadd;
    long id=0;
    int b;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        txtitem=(TextView)findViewById(R.id.item_name5);
        txtcost=(TextView)findViewById(R.id.cost5);
        txtdesc=(TextView)findViewById(R.id.Desc5);
        enterqty=(EditText) findViewById(R.id.enterqty5);
        btn1=(Button) findViewById(R.id.check_cost);
        btn2=(Button) findViewById(R.id.add_cart);
        reference=FirebaseDatabase.getInstance().getReference().child("Items");

        final String uid = getIntent().getStringExtra("uid");
        final String sid = getIntent().getStringExtra("sid");
        final String iname=getIntent().getStringExtra("iname");
        final String cost=getIntent().getStringExtra("cost");
        final String desc=getIntent().getStringExtra("desc");
        final String itemid= getIntent().getStringExtra("itemId");
        reff = FirebaseDatabase.getInstance().getReference().child("Cart");
        cartadd=new Cart();
        final int itid=Integer.parseInt(itemid);
        final int d=Integer.parseInt(cost);

        txtitem.setText(iname);
        txtdesc.setText(desc);
        txtcost.setText(cost);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    final String z=enterqty.getText().toString().trim();
                    b=Integer.parseInt(z);
                    Toast.makeText(CartActivity.this,"Price of this item is Rs. "+b*d,Toast.LENGTH_LONG).show();
                }
                catch(Exception e){
                    Toast.makeText(CartActivity.this,"Enter Quantity",Toast.LENGTH_LONG).show();
                }
            }
        });

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    id=(snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                cartadd.setCost(b*d);
                cartadd.setDesc(desc);
                cartadd.setUserid(uid);
                cartadd.setSellerid(sid);
                cartadd.setItemName(iname);
                cartadd.setQuantity(b);
                cartadd.setItemId(itid);
                cartadd.setCart_No((int) (id+1));
                reff.child(String.valueOf(id+1)).setValue(cartadd);
                Toast.makeText(CartActivity.this,"Succesfully added to the cart",Toast.LENGTH_LONG).show();

                    Intent i =new Intent(CartActivity.this,DisplayItemsForCustomer.class);
                    i.putExtra("uid",uid);
                    i.putExtra("sid",sid);
                    startActivity(i);
                }
                catch(Exception e){
                    Toast.makeText(CartActivity.this,"Error "+e,Toast.LENGTH_LONG).show();

                }}
        });
   }
}