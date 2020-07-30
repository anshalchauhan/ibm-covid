package com.example.ibmhackathon2020.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibmhackathon2020.Add_items;
import com.example.ibmhackathon2020.R;
import com.example.ibmhackathon2020.WatsonAsst;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }


    public LiveData<String> getText() {
        return mText;
    }
}
/*
package com.example.ibmhackathon2020;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.Menu;
        import android.widget.Button;
        import android.widget.Switch;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.material.floatingactionbutton.FloatingActionButton;
        import com.google.android.material.snackbar.Snackbar;
        import com.google.android.material.navigation.NavigationView;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.Objects;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.navigation.NavController;
        import androidx.navigation.Navigation;
        import androidx.navigation.NavInflater;
        import androidx.navigation.ui.AppBarConfiguration;
        import androidx.navigation.ui.NavigationUI;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

public class Admindashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    TextView shopName, phoneNo;
    DatabaseReference reff2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);

        final String sid = getIntent().getStringExtra("sid");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hview = navigationView.getHeaderView(0);

        shopName = (TextView) hview.findViewById(R.id.txtshopname);
        phoneNo = (TextView) hview.findViewById(R.id.txtphoneno);
        reff2 = FirebaseDatabase.getInstance().getReference().child("Seller");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(com.example.ibmhackathon2020.Admindashboard.this, Add_items.class);
                i.putExtra("sid",sid);
                startActivity(i);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navigationView.setCheckedItem(R.id.nav_home);

        reff2.orderByChild("sellerId").equalTo(sid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    String shopname = snapshot.child(sid).child("shopName").getValue().toString();
                    String pno = snapshot.child(sid).child("phoneNo").getValue().toString();
                    if (!shopname.equals("null") && !pno.equals("0")) {
                        shopName.setText(shopname);
                        phoneNo.setText(pno);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(com.example.ibmhackathon2020.Admindashboard.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admindashboard, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void clickact(View view) {
        Intent i = new Intent(com.example.ibmhackathon2020.Admindashboard.this, WatsonAsst.class);
        startActivity(i);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.covidStatus:
                Intent i = new Intent(com.example.ibmhackathon2020.Admindashboard.this,WatsonAsst.class);
                startActivity(i);
                break;
            case R.id.nav_slideshow:
                break;
            default:
                break;
        }
        return true;
    }
}*/