package com.example.lenovo.cart.sore;

import com.example.lenovo.cart.bean.Result;

public interface DataCall<T> {
    void onSuccess(T data);
    void onFailer(Result result);
}
