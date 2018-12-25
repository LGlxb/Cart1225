package com.example.lenovo.cart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.cart.R;
import com.example.lenovo.cart.bean.Data;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {
    private List<Data> mList = new ArrayList();
    private Context context;

    public HomePageAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.flow_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Random random = new Random();
        ViewGroup.LayoutParams layoutParams = viewHolder.text.getLayoutParams();
        layoutParams.height = random.nextInt(200)+50;
        viewHolder.text.setLayoutParams(layoutParams);
        String images = mList.get(i).getImages();
        String[] split = images.split("\\|");
        if (split.length > 0) {
            String replace = split[0].replace("https", "http");
            Glide.with(context).load(replace).into(viewHolder.image);
        }
        viewHolder.text.setText(mList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
        }
    }

    public void addAll(List<Data> data) {
        if (data != null) {
            mList.addAll(data);
        }
    }

    public void clearList() {
        mList.clear();
    }

}
