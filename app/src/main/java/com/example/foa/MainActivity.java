package com.example.foa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button vc;
    private RecyclerView rv;
    private RecyclerView.Adapter a;
    private List<menulist> li;
    private DatabaseReference dbu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vc = (Button)findViewById(R.id.vc);
        dbu = FirebaseDatabase.getInstance().getReference("menulist");
        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));


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
}
