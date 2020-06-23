package com.example.foa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class OpenDetails extends AppCompatActivity {

    private ImageView img;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private Button b1,b2,b3,b4;
    private int n;
    private String imgurl;

    private DatabaseReference dbu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_details);

        img = (ImageView)findViewById(R.id.img);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);

        dbu = FirebaseDatabase.getInstance().getReference("cart");

        final String f = getIntent().getExtras().getString("f");
        final String p = getIntent().getExtras().getString("p");
        imgurl = getIntent().getExtras().getString("imgurl");

        n = Integer.parseInt(tv3.getText().toString());

        Picasso.get().load(imgurl).into(img);
        tv1.setText(f);
        tv2.setText(p);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    n=n-1;
                    tv3.setText(n+"");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=n+1;
                tv3.setText(n+"");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q = tv3.getText().toString();
                String id = dbu.push().getKey();
                cartlist cl = new cartlist(id,q,imgurl,f,p);
                dbu.child(id).setValue(cl);
                Toast.makeText(getApplicationContext(), "Item added to cart!",
                        Toast.LENGTH_LONG).show();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(getBaseContext(),CartActivity.class);
                startActivity(s);
            }
        });

    }
}
