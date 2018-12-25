package com.example.lenovo.cart.waterfallflow;

import com.example.lenovo.cart.bean.Result;
import com.example.lenovo.cart.sore.BasePersenter;
import com.example.lenovo.cart.sore.DataCall;

public class FlowListDataPersenter extends BasePersenter {
    public FlowListDataPersenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Result getData(Object... args) {
        Result result = FlowListDataModel.FlowListData();
        return result;
    }
}
