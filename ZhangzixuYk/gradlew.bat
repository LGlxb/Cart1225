package com.example.lg.zhangzixuweek03_1217;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import adapters.GoodsAdapter;
import bean.GoodsBean;
import core.DataCall;
import presenter.GoodsPresenter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DataCall {

    private ImageView mBtnBack;
    private ImageView mBtnMenu;
    private XRecyclerView mXrecy;
    private int type = 1;
    private GoodsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //数据获取
        initData();
    }

    private void initData() {
        GoodsPresenter presenter = new GoodsPresenter(this);
        mBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 1) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    mXrecy.setLayoutManager(linearLayoutManager);
                    adapter.notifyDataSetChanged();
                    type = 2;
                } else {
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
                    mXrecy.setLayoutManager(gridLayoutManager);
                    adapter.notifyDataSetChanged();
                    type = 1;
                }
            }
        });
    }

    private void initView() {
        mBtnBack = (ImageView) findViewById(R.id.btn_back);
        mBtnMenu = (ImageView) findViewById(R.id.btn_menu);
        mBtnMenu.setOnClickListener(this);
        mXrecy = (XRecyclerView) findViewById(R.id.xrecy);
    