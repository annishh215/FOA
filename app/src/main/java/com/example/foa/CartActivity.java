package com.example.foa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.Adapter ac;
    private List<cartlist> cli;

    private DatabaseReference dbu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        dbu = FirebaseDatabase.getInstance().getReference("cart");

        dbu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cli = new ArrayList<>();
                for (DataSnapshot postsnapshot: dataSnapshot.getChildren()){
                   cartlist Li = new cartlist(postsnapshot.child("q").getValue().toString(),
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
    }
}
