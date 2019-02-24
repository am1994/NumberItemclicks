package com.devmina.droid_ant.numberitemclicks;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class BAdapter extends RecyclerView.Adapter<BAdapter.MyviewHolder> {

   private ItemClickListener itemClickListener;
    private List<Data> mlist;
    private Context mContext;
    private ArrayList<Integer> color;
    BAdapter(List<Data> mList, Context context,ItemClickListener itemClickListener) {
        this.mlist = mList;
        this.mContext = context;
        this.itemClickListener=itemClickListener;
        color=new ArrayList<>();
        color.add(Color.RED);
        color.add(Color.BLUE);
        color.add(Color.GREEN);
        color.add(Color.MAGENTA);

    }



    protected   class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        ConstraintLayout parentLayout;
        int posC,pos,count=0;

        //constructor
        MyviewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.roundView);
            parentLayout=view.findViewById(R.id.parentLayout);

            parentLayout.setOnClickListener(this);



        }

        @Override
        public void onClick(View view) {
            //count=1;
            pos=getAdapterPosition();
            if(pos!=posC)
                count=1;

            else
                count=count+1;

            itemClickListener.clickListener(pos,count);
            posC=pos;

            Toast.makeText(mContext,"posC"+posC,Toast.LENGTH_SHORT).show();

            //count=0;





        }
    }
        @NonNull
        @Override
        public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                //size of the item
            itemView.setLayoutParams(new ConstraintLayout.LayoutParams(150,150));

            return new MyviewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
            //use glide to binding images
            Glide.with(holder.itemView.getContext())
                    .load(mlist.get(position).getImageId())
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.imageView);


            //color the background
            int backgroundColor = color.get(position % color.size());
          holder.parentLayout.setBackgroundColor(backgroundColor);



        }

        @Override
        public int getItemCount() {
            return mlist.size();
        }




}