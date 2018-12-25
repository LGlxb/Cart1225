package adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lg.xuweek3.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import beans.Goods;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private Context context;
    private List<Goods.DataBean> sList;

    public MyAdapter(Context context, List<Goods.DataBean> sList) {
        this.context = context;
        this.sList = sList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.itemlayout, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Goods.DataBean dataBean = sList.get(i);
        myHolder.name.setText(dataBean.getTitle());
        myHolder.name1.setText(dataBean.getPrice());
//        Glide.with(context).load(dataBean.)
        String images1 = dataBean.getImages();
//        String images = mListData.get(position).getImages();
        String[] split = images1.split("\\|");
        if (split.length > 0) {
//            Glide.with(mContext).load(split[0]).into(holder.imageView);
            Glide.with(context).load(split[0]).into(myHolder.img_pic);
        }
    }

    @Override
    public int getItemCount() {
        return sList.size();
    }

    public void addIten(List<Goods.DataBean> data) {
        sList.addAll(data);
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView img_pic;
        private final TextView name;
        private final TextView name1;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img_pic = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.tv1);
            name1 = itemView.findViewById(R.id.tv2);
        }
    }
}
