package com.example.lg.zhangzixuyk;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import adapter.GoodsAdapter;
import bean.GoodsBean;
import bean.Shop;
import core.DataCall;
import presenter.GoodsPresenter;

public class MainActivity extends AppCompatActivity implements DataCall {

    private SearchView mSarch;
    private XRecyclerView mXrecy;
    private int page = 1;
    private int type = 1;
    private String sname = "手机";

    private List<GoodsBean.DataBean> list;
    private GoodsAdapter adapter;
    private GoodsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new GoodsPresenter(this);
        initView();
        //刷新加载
        initRefresh();
        //搜索
        mSarch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                presenter.showGoods(page, s);
                return false;
            }
        });
    }


    private void initRefresh() {
        mXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        presenter.showGoods(page, sname);
                        mXrecy.refreshComplete();
                        Toast.makeText(MainActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        presenter.showGoods(page, sname);
                        mXrecy.loadMoreComplete();
                        Toast.makeText(MainActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });
    }


    private void initView() {
        mSarch = findViewById(R.id.sarch);
        mXrecy = findViewById(R.id.xrecy);
        presenter.showGoods(1, "笔记本");
    }

    @Override
    public void ShowSuccess(GoodsBean goodsBean) {
        list = goodsBean.getData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXrecy.setLayoutManager(linearLayoutManager);
        adapter = new GoodsAdapter(list, context());
        mXrecy.setAdapter(adapter);
    }

    @Override
    public void ShowGoodsSuccess(List<Shop.DataBean> dataBean) {

    }

    @Override
    public void ShowError(String error) {
        Toast.makeText(this, "++++" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
