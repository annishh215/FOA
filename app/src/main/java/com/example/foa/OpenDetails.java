package com.example.foa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class OpenDetails extends AppCompatActivity {

    private ImageView img;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private Button b1,b2,b3;
    private int n;
    private String imgurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_details);

        img = (ImageView)findViewById(R.id.img);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);

        String f = getIntent().getExtras().getString("f");
        String p = getIntent().getExtras().getString("p");
        imgurl = getIntent().getExtras().getString("imgurl");

        n = Integer.parseInt(tv3.getText().toString());

        Picasso.get().load(imgurl).into(img);
        tv1.setText(f);
        tv2.setText(p);
        tv4.setText(imgurl);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (n==1){
                    b1.setEnabled(false);
                }
                else{
                    n=n-1;
                    tv3.setText(n+"");
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=n+1;
                tv3.setText(n+"");
            }
        });




    }
}
