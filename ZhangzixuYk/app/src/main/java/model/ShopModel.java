package model;

import com.google.gson.Gson;

import java.util.List;

import bean.Shop;
import utils.HttpUtils;

/**
 * 购物车M层
 */
public class ShopModel {

    public void showShop(String url, final ShopModel.MainmodelCallback mainmodelCallback) {
        HttpUtils httpUtils = HttpUtils.getHttpUtils();
        httpUtils.doGet(url, new HttpUtils.IOKhttpUtilsCallback() {
            @Override
            public void onFailure(String error) {
                if (mainmodelCallback != null) {
                    mainmodelCallback.getFaild(error);
                }
            }

            @Override
            public void onResponse(String json) {
                if (json != null && !json.equals("")) {
                    if (mainmodelCallback != null) {
                        Shop shop = new Gson().fromJson(json, Shop.class);
                        mainmodelCallback.getSuccess(shop.getData());
                    }
                } else if (mainmodelCallback != null) {
                    mainmodelCallback.getFaild("请求失败");
                }
            }
        });
    }

    //接口回调
    public interface MainmodelCallback {
        void getSuccess(List<Shop.DataBean> dataBean);

        void getFaild(String error);
    }
}
