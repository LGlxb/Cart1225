package com.example.lenovo.cart.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.cart.R;
import com.example.lenovo.cart.adapter.HomePageAdapter;
import com.example.lenovo.cart.bean.Data;
import com.example.lenovo.cart.bean.Result;
import com.example.lenovo.cart.sore.DataCall;
import com.example.lenovo.cart.waterfallflow.FlowListDataPersenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.android.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Fragment implements View.OnClickListener,DataCall<List<Data>> {

    private ImageView mImageFlicking,mImageSearch;
    private EditText mEditSearch;
    private Banner mShuffling;
    private ArrayList<String> list_path = new ArrayList<>(); //放图片地址的集合
    private ArrayList<String> list_title = new ArrayList<>();//放标题的集合
    private RecyclerView mRecyclerView;
    private FlowListDataPersenter mPersenter = new FlowListDataPersenter(this);
    private HomePageAdapter mAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_homepage,null);
        initView();
        initData();
        setBanner();
        initAdapter();
        return view;
    }
    private void initView() {
        // 实现 扫一扫功能
        mImageFlicking = (ImageView) view.findViewById(R.id.image_flicking);
        mImageFlicking.setOnClickListener(this);

        mEditSearch = (EditText) view.findViewById(R.id.edit_search);

        mImageSearch = (ImageView) view.findViewById(R.id.image_search);
        // 实现 无限轮播功能
        mShuffling = (Banner) view.findViewById(R.id.shuffling);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
                (2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        mPersenter.requestData();
    }

    private void initData() {
        list_path.add("http://www.zhaoapi.cn/images/quarter/ad1.png");
        list_path.add("http://www.zhaoapi.cn/images/quarter/ad3.png");
        list_path.add("http://www.zhaoapi.cn/images/quarter/ad4.png");
        list_title.add("第十三界瑞丽模特大赛");
        list_title.add("直播封面标准");
        list_title.add("人气谁最高，金主谁最豪气");
    }

    private void setBanner() {
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        mShuffling.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        mShuffling.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        mShuffling.setImages(list_path);
        //设置轮播图的标题集合
        mShuffling.setBannerTitles(list_title);
        //设置轮播间隔时间
        mShuffling.setDelayTime(2000);
        //设置是否为自动轮播，默认是“是”。
        mShuffling.isAutoPlay(true);
        //必须最后调用的方法，启动轮播图。
        mShuffling.start();
    }

    private void initAdapter() {
        mAdapter = new HomePageAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.image_flicking:
                PermissionUtils.permission(getActivity(), new PermissionUtils.PermissionListener() {
                    @Override
                    public void success() {
                        Intent intent = new Intent(getActivity(), CaptureActivity.class);
                        startActivity(intent);
                    }
                });
                break;
        }
    }

    @Override
    public void onSuccess(List<Data> data) {
        mAdapter.clearList();
        mAdapter.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailer(Result result) {
        Toast.makeText(getActivity(),result.getMsg()+"",Toast.LENGTH_SHORT).show();
    }

    private class MyLoader extends com.youth.banner.loader.ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext()).load(path).into(imageView);
        }
    }
}
