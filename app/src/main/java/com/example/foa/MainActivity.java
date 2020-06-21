package com.example.foa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Button vc;
    private RecyclerView rv;
    private RecyclerView.Adapter a;
    private List<menulist> li;
    private DatabaseReference dbu;
    private DrawerLayout drla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vc = (Button)findViewById(R.id.vc);
        dbu = FirebaseDatabase.getInstance().getReference("menulist");
        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        Toolbar tb = findViewById(R.id.tb);
        setSupportActionBar(tb);

        NavigationView nv = findViewById(R.id.naview);
        nv.setNavigationItemSelectedListener(this);

        drla = findViewById(R.id.drla);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drla, tb, R.string.open_toggle, R.string.close_toggle);
        drla.addDrawerListener(toggle);
        toggle.syncState();



        dbu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                li = new ArrayList<>();
                for (DataSnapshot postsnapshot: dataSnapshot.getChildren()){
                    menulist Li = new menulist(postsnapshot.child("imgurl").getValue().toString(),
                            ""+ postsnapshot.child("f").getValue().toString(),
                            "" + postsnapshot.child("p").getValue().toString());
                    li.add(Li);
                }
                a = new adapter(li, getApplicationContext());
                rv.setAdapter(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        vc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),CartActivity.class);
                startActivity(i);
            }
        });



    }

    @Override
    public void onBackPressed() {
        if(drla.isDrawerOpen(GravityCompat.START)){
            drla.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.exit:
                finish();
                System.exit(0);
                break;
        }
        return true;
    }
}
