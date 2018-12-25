package presenter;

import bean.GoodsBean;
import core.DataCall;
import model.GoodsModel;

public class GoodsPresenter extends BasePresenter<DataCall> {


    private GoodsModel goodsModel;

    public GoodsPresenter(DataCall view) {
        super(view);

    }

    @Override
    protected void initModel() {
        goodsModel = new GoodsModel();
    }

    public void showGoods(int page, String name) {
        goodsModel.showGoods(page, name, new GoodsModel.MainmodelCallback() {
            @Override
            public void getSuccess(GoodsBean goodsBean) {
                view.ShowSuccess(goodsBean);
            }

            @Override
            public void getFaid(String error) {
                view.ShowError(error);
            }
        });
    }
}
