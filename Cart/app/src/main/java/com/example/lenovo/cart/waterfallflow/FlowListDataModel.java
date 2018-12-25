package com.example.lenovo.cart.waterfallflow;

import com.example.lenovo.cart.bean.Data;
import com.example.lenovo.cart.bean.Result;
import com.example.lenovo.cart.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class FlowListDataModel {
    public static Result FlowListData() {
        String data = Utils.Get("http://www.zhaoapi.cn/product/getProducts?pscid=1");
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<Result<List<Data>>>() {
            }.getType();
            Result result = gson.fromJson(data, type);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        Result result = new Result();
        result.setCode(-1);
        result.setMsg("数据解析异常");
        return result;
    }
}
