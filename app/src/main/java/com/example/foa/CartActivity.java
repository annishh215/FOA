package com.example.foa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private Button rb,cb,pb;

    private RecyclerView rv;
    private RecyclerView.Adapter ac;
    private List<cartlist> cli;
    private int t;
    private int c,n;

    private DatabaseReference dbu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        rb = (Button)findViewById(R.id.rb);
        cb = (Button)findViewById(R.id.cb);
        pb = (Button)findViewById(R.id.pb);

        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        dbu = FirebaseDatabase.getInstance().getReference("cart");

        dbu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                t = 0;
                for (DataSnapshot totsnapshot: dataSnapshot.getChildren()){
                    c = Integer.parseInt(totsnapshot.child("p").getValue().toString());
                    n = Integer.parseInt(totsnapshot.child("q").getValue().toString());
                    t = t + (n*c);
                }
                pb.setText("Place order cart total: "+t);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cli = new ArrayList<>();
                for (DataSnapshot postsnapshot: dataSnapshot.getChildren()){
                   cartlist Li = new cartlist(postsnapshot.getKey(),
                           postsnapshot.child("q").getValue().toString(),
                           postsnapshot.child("imgurl").getValue().toString(),
                           postsnapshot.child("f").getValue().toString(),
                           postsnapshot.child("p").getValue().toString());
                   cli.add(Li);
                }
                ac = new cartadapter(cli, getApplicationContext());
                rv.setAdapter(ac);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbu.removeValue();
            }
        });

        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbu.removeValue();
                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Order Placed!",
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}
