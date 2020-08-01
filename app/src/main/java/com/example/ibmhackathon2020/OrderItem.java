package com.example.ibmhackathon2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibmhackathon2020.Model.Cart;
import com.example.ibmhackathon2020.ViewHolder.OrderItemViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderItem extends AppCompatActivity {

    RecyclerView recyclerView6;
    RecyclerView.LayoutManager layoutManager6;
    FirebaseRecyclerAdapter<Cart, OrderItemViewHolder> adapter6;
    FirebaseDatabase database;
    DatabaseReference reference;
    TextView txtmsg;
    EditText enterqty;
    String sid,uid,c;
    int d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item);

        recyclerView6 = (RecyclerView)findViewById(R.id.recycler_view6);
        recyclerView6.setHasFixedSize(true);
        layoutManager6=new LinearLayoutManager(this);
        recyclerView6.setLayoutManager(layoutManager6);
        uid = getIntent().getStringExtra("uid");
        sid = getIntent().getStringExtra("sid");
        reference=FirebaseDatabase.getInstance().getReference().child("Cart");
            //Toast.makeText(OrderItem.this,"hi"+uid,Toast.LENGTH_LONG).show();
        showList2();

        FloatingActionButton fab = findViewById(R.id.orders);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


        private void showList2() {

        FirebaseRecyclerOptions op6= new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(reference.orderByChild("userid").equalTo(uid),Cart.class)
                .build();

        adapter6= new FirebaseRecyclerAdapter<Cart, OrderItemViewHolder>(op6) {
            @Override
            protected void onBindViewHolder(@NonNull OrderItemViewHolder holder, int i, @NonNull Cart cart) {
                holder.txtitemname6.setText(cart.getItemName());
                holder.txtcost6.setText(String.valueOf(cart.getCost()));
                holder.txtqty6.setText(String.valueOf(cart.getQuantity()));
            }

            @NonNull
            @Override
            public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cart_list,parent,false);
                return new OrderItemViewHolder(view);
            }

        };

        adapter6.startListening();
        adapter6.notifyDataSetChanged();
        recyclerView6.setAdapter(adapter6);
    }
}