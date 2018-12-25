package com.example.lg.zhangzixuyk;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import adapter.ShopAdapter;
import bean.GoodsBean;
import bean.Shop;
import core.DataCall;
import presenter.ShopPresenter;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener, DataCall {

    private ExpandableListView ex_listView;
    private CheckBox all_box;
    private TextView all_price;
    private Button jiesuan_btn;
    private ShopAdapter shopAdapter;
    private String url = "http://www.zhaoapi.cn/product/getCarts?uid=71";
    private ShopPresenter shopPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        //A.获取控件
        initView();
        //B.获取数据
        shopPresenter = new ShopPresenter(this);
        shopPresenter.showShop(url);
    }


    //-------底部全选框的点击事件--------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_box:
                boolean allSelected = shopAdapter.isAllSelected();
                shopAdapter.changAllCheckBox(!allSelected);
                shopAdapter.notifyDataSetChanged();
                //刷新底部的数据显示
                refreshAllShop();
                break;
        }
    }

    //A.初始化控件
    private void initView() {
        ex_listView = findViewById(R.id.ex_listView);
        all_box = findViewById(R.id.all_box);
        all_price = findViewById(R.id.all_price);
        jiesuan_btn = findViewById(R.id.jiesuan_btn);
        all_box.setOnClickListener(this);
    }

    //--------刷新底部---------
    private void refreshAllShop() {
        //判断是否所有商品都被选中
        boolean allSelected = shopAdapter.isAllSelected();
        //设置给checkbox
        all_box.setChecked(allSelected);
        //计算总计
        float price = shopAdapter.TotalPrice();
        all_price.setText("总计" + price);
        //计算数量
        int number = shopAdapter.TotalNumber();
        jiesuan_btn.setText("去结算（" + number + ")");

    }

    @Override
    public void ShowSuccess(GoodsBean goodsBean) {
    }

    @Override
    public void ShowGoodsSuccess(List<Shop.DataBean> dataBean) {
        //B1.获取适配器
        shopAdapter = new ShopAdapter(dataBean);
        //B2.对适配器设置监听（加减器，组，子条目复选框改变）
        shopAdapter.setOnCartListChangeListener(new ShopAdapter.onCartListChangeListener() {
            //------B2.1.组的复选框被点击------
            @Override
            public void onParentCheckedChange(int groupPosition) {
                boolean parentAllSelect = shopAdapter.isParentAllSelect(groupPosition);
                shopAdapter.changeSellerAllProduct(groupPosition, !parentAllSelect);
                shopAdapter.notifyDataSetChanged();
                //刷新底部
                refreshAllShop();

            }

            //-------B2.2.子条目的复选框被点击-------
            @Override
            public void onChildCheckedChange(int groupPosition, int childPosition) {
                shopAdapter.changeChild(groupPosition, childPosition);
                shopAdapter.notifyDataSetChanged();
                refreshAllShop();
            }

            //-------B2.3.加减器被点击------
            @Override
            public void onAddSubNumberChange(int groupPosition, int childPosition, int number) {
                shopAdapter.changProductNumber(groupPosition, childPosition, number);
                shopAdapter.notifyDataSetChanged();
                refreshAllShop();
            }
        });
        //B3.设置adapter对象
        ex_listView.setAdapter(shopAdapter);
        //B4.展开二级列表
        for (int i = 0; i < dataBean.size(); i++) {
            ex_listView.expandGroup(i);
        }

    }


    @Override
    public void ShowError(String error) {
        Toast.makeText(ShopActivity.this, "66666    +." + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return this;
    }
}

