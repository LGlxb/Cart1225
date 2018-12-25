package presenter;

import java.util.List;

import bean.Shop;
import core.DataCall;
import model.ShopModel;

public class ShopPresenter extends BasePresenter<DataCall> {

    private ShopModel shopModel;

    public ShopPresenter(DataCall view) {
        super(view);
    }

    @Override
    protected void initModel() {
        shopModel = new ShopModel();
    }

    public void showShop(String url) {
        shopModel.showShop(url, new ShopModel.MainmodelCallback() {
            @Override
            public void getSuccess(List<Shop.DataBean> dataBean) {
                view.ShowGoodsSuccess(dataBean);
            }

            @Override
            public void getFaild(String error) {
                view.ShowError(error);
            }
        });
    }
}
