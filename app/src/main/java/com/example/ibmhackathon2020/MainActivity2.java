package com.example.ibmhackathon2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ibmhackathon2020.Model.ItemCategory;
import com.example.ibmhackathon2020.Model.ShopCategory;
import com.example.ibmhackathon2020.ViewHolder.ItemCategoryViewHolder;
import com.example.ibmhackathon2020.ViewHolder.ShopCategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.StringValue;

public class MainActivity2 extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    String uid;
    RecyclerView recyclerView1;
    RecyclerView.LayoutManager layoutManager1;
    FirebaseRecyclerAdapter<ShopCategory, ShopCategoryViewHolder> adapter1;
    FirebaseDatabase database1;
    DatabaseReference reference1,reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        uid = getIntent().getStringExtra("uid");
        database1=FirebaseDatabase.getInstance();
        reference1=database1.getReference("Seller");
        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        recyclerView1 = (RecyclerView)findViewById(R.id.recycler_view1);
        recyclerView1.setHasFixedSize(true);
        layoutManager1=new GridLayoutManager(this,2);
        recyclerView1.setLayoutManager(layoutManager1);

                dl = (DrawerLayout) findViewById(R.id.activity_main2);
                t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

                dl.addDrawerListener(t);
                t.syncState();

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                nv = (NavigationView) findViewById(R.id.nv);
                nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.home1:
                                Intent j= new Intent(getApplicationContext(), MainActivity2.class);
                                j.putExtra("uid",uid);
                                startActivity(j);
                                break;
                            case R.id.covid1:
                                Intent k= new Intent(getApplicationContext(), WatsonAsst.class);
                                startActivity(k);
                                break;
                            case R.id.logout1:
                                Intent l= new Intent(getApplicationContext(), login_activity.class);
                                startActivity(l);
                                break;
                            default:
                                return true;
                        }
                        return true;
                    }
                });

        /*reff.orderByChild("userId").equalTo(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("address").getValue().equals("null") || snapshot.child("phone_no").getValue().equals("0") || snapshot.child("pincode").getValue().equals("0")) {
                    Intent j= new Intent(getApplicationContext(), MainActivity2.class);
                    j.putExtra("uid",uid);
                    startActivity(j);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });*/

        showlist1(uid);
            }

    private void showlist1(final String uid) {
        FirebaseRecyclerOptions options1= new FirebaseRecyclerOptions.Builder<ShopCategory>()
                .setQuery(reference1,ShopCategory.class)
                .build();

        adapter1 = new FirebaseRecyclerAdapter<ShopCategory, ShopCategoryViewHolder>(options1) {
            @Override
            protected void onBindViewHolder(@NonNull ShopCategoryViewHolder holder, int i, @NonNull ShopCategory shopCategory) {

                holder.txtshopName.setText(shopCategory.getShopName());
                holder.txtownerName.setText(shopCategory.getOwnerName());
                holder.txtlocation.setText(shopCategory.getLocation());
                holder.txtphonenum.setText(shopCategory.getPhoneNo());
                holder.txtpincode.setText(shopCategory.getPinCode());
                final String sid=shopCategory.getSellerId();

                holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity2.this,DisplayItemsForCustomer.class);
                        i.putExtra("uid",uid);
                        i.putExtra("sid",sid);
                        startActivity(i);
                    }
                });
            }

            @NonNull
            @Override
            public ShopCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.shop_list,parent,false);
                return new ShopCategoryViewHolder(view);
            }
        };

        adapter1.startListening();
        adapter1.notifyDataSetChanged();
        recyclerView1.setAdapter(adapter1);
    }

    @Override
            public boolean onOptionsItemSelected(MenuItem item) {

                if (t.onOptionsItemSelected(item))
                    return true;

                return super.onOptionsItemSelected(item);
            }
}