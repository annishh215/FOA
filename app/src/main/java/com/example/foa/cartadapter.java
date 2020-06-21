package com.example.foa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foa.R;
import com.example.foa.cartlist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class cartadapter extends RecyclerView.Adapter<cartadapter.viewholder> {

    private List<cartlist> cli;
    private Context ca;


    public cartadapter(List<cartlist> cli, Context ca) {
        this.cli = cli;
        this.ca = ca;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartlist,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        cartlist Li = cli.get(position);
        holder.tv4.setText(Li.getF());
        holder.tv5.setText(Li.getP());
        holder.tv6.setText(Li.getQ());

        String imgurl = Li.getImgurl();
        Picasso.get().load(imgurl).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return cli.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        private TextView tv4,tv5,tv6;
        private ImageView img;
        private Button rb;
        private DatabaseReference dbu;



        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv4 = (TextView)itemView.findViewById(R.id.tv4);
            tv5 = (TextView)itemView.findViewById(R.id.tv5);
            tv6 = (TextView)itemView.findViewById(R.id.tv6);
            img = (ImageView)itemView.findViewById(R.id.img);
            rb = (Button)itemView.findViewById(R.id.rb);
            dbu = FirebaseDatabase.getInstance().getReference("cart");


            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cartlist cl = cli.get(getAbsoluteAdapterPosition());
                    dbu.child(cl.id).setValue(null);
                }
            });

        }
    }
}
