package com.daakyou.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daakyou.R;
import com.daakyou.pojo.couponinsert;

import java.util.List;

public class rvadp extends RecyclerView.Adapter<rvadp.viewholder> {
    List<couponinsert> list;

    public rvadp(List<couponinsert> list)
    {
        this.list=list;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.tx2,parent,false);
        viewholder vh=new viewholder(parent.getContext(),v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.textView.setText(list.get(position).getCouppon());
        if(list.get(position).getCoupontype().equals("active"))
        {
          holder.textView2.setText(list.get(position).getCoupontype());
          holder.textView2.setTextColor(Color.GREEN);
        }
        else
        {
            holder.textView2.setText(list.get(position).getCoupontype());
            holder.textView2.setTextColor(Color.RED);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder
    {
        Context context;
        TextView textView,textView2;
        ImageView imageView;
        public viewholder(Context context, View itemView) {
            super(itemView);

            this.context=context;
            textView=(TextView)itemView.findViewById(R.id.txd);
            textView2=(TextView)itemView.findViewById(R.id.txd2);
            imageView=(ImageView)itemView.findViewById(R.id.image);

        }
    }
}
