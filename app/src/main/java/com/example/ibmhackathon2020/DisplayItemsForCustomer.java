package com.example.ibmhackathon2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibmhackathon2020.Model.ItemCategory;
import com.example.ibmhackathon2020.ViewHolder.ItemCategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DisplayItemsForCustomer extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<ItemCategory, ItemCategoryViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference reference;
    TextView txtmsg;
    EditText enterqty;
    String sid,uid,c;
    int d;
    //ArrayList<Integer> item_lists=new ArrayList<>();
    //ArrayList<Integer> item_lists_qty =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_items_for_customer);

            uid = getIntent().getStringExtra("uid");
            sid = getIntent().getStringExtra("sid");
            database=FirebaseDatabase.getInstance();
            reference=database.getReference().child("Items");
            recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            layoutManager=new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            //enterqty=(EditText) findViewById(R.id.enterqty);

       /* FloatingActionButton fab = findViewById(R.id.Order);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DisplayItemsForCustomer.this,OrderItem.class);
                i.putExtra("sid",sid);
                i.putExtra("uid",uid);
                startActivity(i);
                //Toast.makeText(DisplayItemsForCustomer.this,"hi"+item_lists,Toast.LENGTH_LONG).show();
            }
        });*/

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
                            .inflate(R.layout.item_category_for_cusomer,viewGroup,false);
                    return new ItemCategoryViewHolder(view);
                }

                @Override
                protected void onBindViewHolder(@NonNull final ItemCategoryViewHolder holder, int i, @NonNull final ItemCategory itemCategory) {
                    int a=itemCategory.getQty();
                    int p=itemCategory.getCost();
                    final int b = itemCategory.getItemId();
                    final String c=String.valueOf(b);

                    holder.txtitemname1.setText(itemCategory.getItemName());
                    holder.txtcost1.setText(String.valueOf(p));
                    holder.txtqty1.setText(String.valueOf(a));

                   // Toast.makeText(DisplayItemsForCustomer.this,"A"+b,Toast.LENGTH_LONG).show();

                    holder.pl1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(DisplayItemsForCustomer.this,CartActivity.class);
                            i.putExtra("uid",uid);
                            i.putExtra("itemId",c);
                            i.putExtra("iname",itemCategory.getItemName());
                            i.putExtra("cost",(String.valueOf(itemCategory.getCost())));
                            i.putExtra("desc",itemCategory.getDesc());
                            i.putExtra("sid",sid);
                            startActivity(i);
                        }
                    });


                //if(holder.selectitem.isChecked()){
                       // item_lists.add(itemCategory.getItemId());

                    //}
                }
            };

            adapter.startListening();
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
    }

}