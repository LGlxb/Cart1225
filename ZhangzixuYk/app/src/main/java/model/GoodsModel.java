package model;

import com.google.gson.Gson;

import bean.GoodsBean;
import core.DataCall;
import utils.HttpUtils;

public class GoodsModel {
    public void showGoods(int page, String name, final MainmodelCallback mainmodelCallback) {
        HttpUtils httpUtils = HttpUtils.getHttpUtils();
        String url = "http://www.zhaoapi.cn/product/searchProducts?keywords=" + name + "&page=" + page;
        httpUtils.doGet(url, new HttpUtils.IOKhttpUtilsCallback() {
            @Override
            public void onFailure(String error) {
                if (mainmodelCallback != null) {
                    mainmodelCallback.getFaid(error);
                }
            }

            @Override
            public void onResponse(String json) {
                GoodsBean goodsBean = new Gson().fromJson(json, GoodsBean.class);

                if (goodsBean.getCode().equals("0")) {
                    if (mainmodelCallback != null) {
                        mainmodelCallback.getSuccess(goodsBean);
                    }
                } else {
                    if (mainmodelCallback != null) {
                        mainmodelCallback.getFaid("请求失败");
                    }
                }
            }
        });
    }

    public interface MainmodelCallback {
        void getSuccess(GoodsBean goodsBean);

        void getFaid(String error);
    }
}
