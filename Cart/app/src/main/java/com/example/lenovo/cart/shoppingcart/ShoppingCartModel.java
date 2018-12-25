package com.example.lenovo.cart.shoppingcart;

import com.example.lenovo.cart.bean.Result;
import com.example.lenovo.cart.bean.Shop;
import com.example.lenovo.cart.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ShoppingCartModel {
    public static Result goodsList() {
        String data = Utils.Get("http://www.zhaoapi.cn/product/getCarts?uid=71");
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<Result<List<Shop>>>() {
            }.getType();
            Result result = gson.fromJson(data, type);
            return result;
        } catch (Exception e) {

        }
        Result result = new Result();
        result.setCode(-1);
        result.setMsg("数据解析异常");
        return result;
    }
}
