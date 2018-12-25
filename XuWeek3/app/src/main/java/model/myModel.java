package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import beans.Goods;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import presenter.dataPresenter;
import utils.HttpUtils;

public class myModel implements dataModel{
    @Override
    public void getData(String url, final dataPresenter dataPresenter) {
        HttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();

                Gson gson = new Gson();

                Goods goods = gson.fromJson(json, Goods.class);

                List<Goods.DataBean> list=goods.getData();

                dataPresenter.success(list);

            }
        });
    }
}
