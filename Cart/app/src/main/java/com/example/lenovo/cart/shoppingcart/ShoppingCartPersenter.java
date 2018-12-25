package com.example.lenovo.cart.shoppingcart;

import com.example.lenovo.cart.bean.Result;
import com.example.lenovo.cart.sore.BasePersenter;
import com.example.lenovo.cart.sore.DataCall;

public class ShoppingCartPersenter extends BasePersenter{

    public ShoppingCartPersenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Result getData(Object... args) {
        Result result = ShoppingCartModel.goodsList();
        return result;
    }
}
