package adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lg.zhangzixuyk.R;
import com.example.lg.zhangzixuyk.ShopActivity;


import java.util.List;

import bean.GoodsBean;

public class GoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GoodsBean.DataBean> list;
    private Context context;

    public GoodsAdapter(List<GoodsBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemlayout,
                viewGroup, false);
        return (new ViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {

        ((ViewHolder) viewHolder).item_dec.setText(list.get(position).getTitle());
        String images = list.get(position).getImages();
        String imageurl = "http" + images.substring(5);

        String[] split = imageurl.split("\\|");
        if (split.length > 0) {
            Glide.with(context).load(split[0]).into(((ViewHolder) viewHolder).item_img);
        }
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);
                alpha.setDuration(5000);
                alpha.start();
                return true;
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShopActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView item_dec;
        private ImageView item_img;

        public ViewHolder(View itemView) {
            super(itemView);
            item_img = itemView.findViewById(R.id.img_icon);
            item_dec = itemView.findViewById(R.id.txt_name);
        }
    }

}