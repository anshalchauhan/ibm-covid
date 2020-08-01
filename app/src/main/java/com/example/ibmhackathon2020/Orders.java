package com.example.ibmhackathon2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ibmhackathon2020.Model.Cart;
import com.example.ibmhackathon2020.Model.ShopCategory;
import com.example.ibmhackathon2020.ViewHolder.OrderItemViewHolder;
import com.example.ibmhackathon2020.ViewHolder.ShopCategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Orders extends AppCompatActivity {

    RecyclerView recyclerView1;
    RecyclerView.LayoutManager layoutManager1;
    FirebaseRecyclerAdapter<Cart, OrderItemViewHolder> adapter1;
    FirebaseDatabase database1;
    DatabaseReference reference1,reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        final String sid = getIntent().getStringExtra("sid");
        reff = FirebaseDatabase.getInstance().getReference().child("Cart");
        recyclerView1 = (RecyclerView)findViewById(R.id.recycler_view3);
        recyclerView1.setHasFixedSize(true);
        layoutManager1=new GridLayoutManager(this,2);
        recyclerView1.setLayoutManager(layoutManager1);

        show(sid);
    }

    private void show(String sid){

        FirebaseRecyclerOptions op2= new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(reff.orderByChild("sellerid").equalTo(sid),Cart.class)
                .build();

        adapter1=new FirebaseRecyclerAdapter<Cart, OrderItemViewHolder>(op2) {
            @Override
            protected void onBindViewHolder(@NonNull OrderItemViewHolder holder, int i, @NonNull Cart cart) {
                holder.in.setText(cart.getItemName());
                holder.ic.setText(String.valueOf(cart.getCost()));
                holder.iq.setText(String.valueOf(cart.getQuantity()));
            }

            @NonNull
            @Override
            public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.customerordered,parent,false);
                return  new OrderItemViewHolder(v);

            }
        };
        adapter1.startListening();
        adapter1.notifyDataSetChanged();
        recyclerView1.setAdapter(adapter1);
    }
}