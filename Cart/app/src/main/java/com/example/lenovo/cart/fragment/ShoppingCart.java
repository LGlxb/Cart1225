package com.example.lenovo.cart.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.cart.R;
import com.example.lenovo.cart.adapter.ShoppingCartAdapter;
import com.example.lenovo.cart.bean.Result;
import com.example.lenovo.cart.bean.Shop;
import com.example.lenovo.cart.shoppingcart.ShoppingCartPersenter;
import com.example.lenovo.cart.sore.DataCall;

import java.util.List;

public class ShoppingCart extends Fragment implements DataCall<List<Shop>>,ShoppingCartAdapter.TotalPriceListener{
    private ExpandableListView mListCart;
    private CheckBox mCheckAll;
    private TextView mGoodsSumPrice;
    private ShoppingCartPersenter mPersenter = new ShoppingCartPersenter(this);
    private ShoppingCartAdapter mAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_shoppingcart,null);
        initView();
        mAdapter = new ShoppingCartAdapter(getActivity());
        mListCart.setAdapter(mAdapter);
        mPersenter.requestData();
        mAdapter.setTotalPriceListener(this);
        mListCart.setGroupIndicator(null);
        mListCart.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
        mCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mAdapter.checkAll(isChecked);
            }
        });

        return view;
    }

    private void initView() {
        mListCart = view.findViewById(R.id.list_cart);
        mCheckAll = view.findViewById(R.id.check_all);
        mGoodsSumPrice = view.findViewById(R.id.goods_sum_price);
    }

    @Override
    public void totalPrice(double totalPrice) {
        mGoodsSumPrice.setText(String.valueOf(totalPrice));
    }

    @Override
    public void onSuccess(List<Shop> data) {
        mAdapter.addAll(data);
        int groupCount = data.size();
        for (int i = 0; i < groupCount; i++) {
            mListCart.expandGroup(i);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailer(Result result) {
        Toast.makeText(getActivity(), result.getCode() + "   " + result.getMsg(), Toast.LENGTH_LONG).show();
    }
}
