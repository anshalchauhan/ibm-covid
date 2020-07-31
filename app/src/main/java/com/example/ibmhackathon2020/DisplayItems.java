package com.example.ibmhackathon2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.ibmhackathon2020.Model.ItemCategory;
import com.example.ibmhackathon2020.ViewHolder.ItemCategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayItems extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<ItemCategory, ItemCategoryViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference reference;
    TextView txtmsg;
    String sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items);

        sid = getIntent().getStringExtra("sid");
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("Items");
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // txtmsg=(TextView)findViewById(R.id.txtmsg);

        /*reference.orderByChild("sellerId").equalTo(sid).addValueEventListener(new ValueEventListener() {
           // @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    showList();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        // reference.orderByChild("sellerId").equalTo(sid)
        showList();
    }
    private void showList(){
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<ItemCategory>()
                .setQuery(reference.orderByChild("sellerId").equalTo(sid),ItemCategory.class)
                .build();

        adapter= new FirebaseRecyclerAdapter<ItemCategory,ItemCategoryViewHolder>(options) {

            @NonNull
            @Override
            public ItemCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.item_category,viewGroup,false);
                return new ItemCategoryViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ItemCategoryViewHolder holder, int i, @NonNull ItemCategory itemCategory) {
                int a=itemCategory.getQty();
                int b = itemCategory.getCost();
                holder.txtitemname.setText(itemCategory.getItemName());
                holder.txtcost.setText(String.valueOf(a));
                holder.txtqty.setText(String.valueOf(b));
            }
        };

        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    public void clickact1(View view) {
        Intent i = new Intent(getApplicationContext(),Add_items.class);
        i.putExtra("sid",sid);
        startActivity(i);
    }
}