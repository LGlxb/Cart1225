package com.example.lg.xuweek3;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import adapters.MyAdapter;
import beans.Goods;
import presenter.myPresenter;
import view.DataCall;

public class MainActivity extends AppCompatActivity implements DataCall {

    private ImageView mImage;
    private RecyclerView mRecy;
    private String url = "https://www.zhaoapi.cn/product/getProducts?pscid=2";
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<Goods.DataBean> list = (List<Goods.DataBean>) msg.obj;
            MyAdapter myAdapter = new MyAdapter(MainActivity.this, list);
            rec.setAdapter(myAdapter);

        }
    };
    private RecyclerView rec;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rec = findViewById(R.id.recy);
        final ImageView image=findViewById(R.id.image);

        myPresenter presenter = new myPresenter(this);
        presenter.netWork(url);
        rec.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                if (i%2==0){
                    rec.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                }else {
                    rec.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                }
                ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(image,"rotation",0f,180f);
                objectAnimator.setDuration(500);
                objectAnimator.start();
            }
        });
    }

    @Override
    public void toData(List<Goods.DataBean> list) {
        Message msg = new Message();
        msg.obj= list;
        handler.sendMessage(msg);
    }
}
