package com.example.foa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.viewholder> {

    private List<menulist> li;
    private Context c;
    private String imgurl;

    public adapter(List<menulist> li, Context c) {
        this.li = li;
        this.c = c;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menulist,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        menulist Li = li.get(position);
        holder.tv1.setText(Li.getF());
        holder.tv2.setText(Li.getP());
        imgurl = Li.getImgurl();
        holder.imgURL = imgurl;
        Picasso.get().load(imgurl).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return li.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView tv1;
        private TextView tv2;
        private String imgURL="";
        public viewholder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.img);
            tv1 = (TextView)itemView.findViewById((R.id.tv1));
            tv2 = (TextView)itemView.findViewById(R.id.tv2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent s = new Intent(c,OpenDetails.class);
                    s.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    s.putExtra("f",tv1.getText().toString());
                    s.putExtra("p", tv2.getText().toString());
                    s.putExtra("imgurl", imgURL);
                    c.startActivity(s);
                }
            });



        }
    }
}
