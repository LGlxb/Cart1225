package com.example.lenovo.cart.sore;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.lenovo.cart.bean.Result;

public abstract class BasePersenter {

    DataCall dataCall;

    public BasePersenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }
    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Result result = (Result) msg.obj;
            if (result.getCode() == 0) {
                dataCall.onSuccess(result.getData());
            } else {
                dataCall.onFailer(result);
            }
        }
    };

    public void requestData(final Object... args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = mHandler.obtainMessage();
                message.obj = getData(args);
                mHandler.sendMessage(message);
            }
        }).start();
    }

    protected abstract Result getData(Object... args);

    public void unBindCall() {
        this.dataCall = null;
    }
}
