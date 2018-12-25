package presenter;

import java.util.List;

import beans.Goods;
import model.myModel;
import view.DataCall;

public class myPresenter implements dataPresenter {
    private final DataCall dataCall;
    private final model.myModel myModel;

    public myPresenter(DataCall dataCall) {
        this.dataCall = dataCall;
        myModel = new myModel();
    }

    @Override
    public void success(List<Goods.DataBean> list) {
        dataCall.toData(list);
    }

    @Override
    public void error() {

    }

    public void netWork(String url) {
        myModel.getData(url, this);
    }
}
