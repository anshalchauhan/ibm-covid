package com.example.ibmhackathon2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibmhackathon2020.Model.ItemCategory;
import com.example.ibmhackathon2020.ViewHolder.ItemCategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<ItemCategory, ItemCategoryViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference reference;
    TextView txtmsg;
    EditText enterqty;
    String sid, uid, c;
    int d;
    ArrayList<Integer> item_lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        uid = getIntent().getStringExtra("uid");
        sid = getIntent().getStringExtra("sid");
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Items");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        showList();
    }

    private void showList() {

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<ItemCategory>()
                .setQuery(reference, ItemCategory.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<ItemCategory, ItemCategoryViewHolder>(options) {

            @NonNull
            @Override
            public ItemCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.cart_list, viewGroup, false);
                return new ItemCategoryViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull final ItemCategoryViewHolder holder, int i, @NonNull final ItemCategory itemCategory) {
                int a = itemCategory.getQty();
                int b = itemCategory.getCost();

                holder.txtiname2.setText(itemCategory.getItemName());
                holder.txtcost2.setText(String.valueOf(a));
                holder.txtqty2.setText(String.valueOf(b));
                Toast.makeText(CartActivity.this,"hi",Toast.LENGTH_LONG).show();

            }
        };

        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}